package kr.ac.kopo.ReadyToTravel.member;

import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import kr.ac.kopo.ReadyToTravel.util.CacheConfig;
import kr.ac.kopo.ReadyToTravel.util.FileUpload;
import kr.ac.kopo.ReadyToTravel.util.PassEncode;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final JavaMailSender javaMailSender;
    private final MailService mailService;
    private final CacheConfig cacheConfig;


    private final MemberCustomRepository memberCustomRepository;


    public MemberServiceImpl(MemberRepository memberRepository, JavaMailSender javaMailSender, MailService mailService, CacheConfig cacheConfig, MemberCustomRepository memberCustomRepository) {
        this.memberRepository = memberRepository;
        this.javaMailSender = javaMailSender;
        this.mailService = mailService;
        this.cacheConfig = cacheConfig;
        this.memberCustomRepository = memberCustomRepository;
    }

    @Override
    public boolean checkId(String id) {
        MemberDTO member = memberCustomRepository.checkId(id);

        if (member == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void singUp(MemberDTO memberDTO) {


        memberRepository.save(MemberEntity
                .builder()
                .memberId(memberDTO.getMemberId())
                .password(PassEncode.encode(memberDTO.getPassword()))
                .email(memberDTO.getEmail())
                .name(memberDTO.getName())
                .signupDate(new Date())
                .profileIMG("defaultProfile.png")
                .phoneNum(memberDTO.getPhoneNum())
                .build());

//        memberDTO.setSignupDate(new Date());
//        MemberEntity entity = memberDTO.convertToEntity(memberDTO);
//        entity.setPassword(PassEncode.encode(entity.getPassword()));
    }

    @Override
    public MemberDTO login(MemberDTO memberDTO) {
        MemberDTO member = memberCustomRepository
                .findIdAndPassword(memberDTO.getMemberId(), PassEncode.encode(memberDTO.getPassword()));

        if (member == null) {
            return null;
        }
        return member;
    }


    @Override
    public boolean initPass(String id, String email) {

        MemberEntity findMember = memberCustomRepository.findByIdAndEmail(id, email);

        // 사용자가 입력한 ID, Email 을 통한 조회 결과가 없을 때 false를 반환 합니다
        if (findMember == null) {
            System.out.println("아이디와 이메일을 통한 조회 결과 없음");
            return false;
        }

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        uuid = uuid.substring(0, 8);

        MemberEntity updatedMember = MemberEntity.builder()
                .num(findMember.getNum())
                .memberId(findMember.getMemberId())
                .name(findMember.getName())
                .profileIMG(findMember.getProfileIMG())
                .signupDate(findMember.getSignupDate())
                .email(findMember.getEmail())
                .phoneNum(findMember.getPhoneNum())
                .build();

        updatedMember.setPassword(PassEncode.encode(uuid));


        memberRepository.save(updatedMember);

        MailService mailService = new MailService(javaMailSender);
        mailService.sendMailForPass(findMember.getEmail(), uuid);

        return true;
    }

    @Override
    public boolean sendEmailCode(String email) {
        MemberDTO emailCheck = memberCustomRepository.findByEmail(email);

        if (emailCheck == null) {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            uuid = uuid.substring(0, 8);

            mailService.sendMailForEmail(email, uuid);
            cacheConfig.putValue(email, uuid);

            return true;

        } else {

            return false;
        }

    }


    /**
     * 캐시에 저장된 인증 코드와 이메일을 통한 인증
     *
     * @param email
     * @param mailValidateKey
     * @return
     */
    @Override
    public boolean validateCode(String email, String mailValidateKey) {
        if (cacheConfig.getValue(email) == null || !cacheConfig.getValue(email).equals(mailValidateKey)) {

            return false;
        } else {


            return true;
        }
    }

    @Override
    public MemberEntity profileUpdate(Long num, MemberDTO updateInfo) {

        MemberEntity member = memberRepository.findByNum(num);

        if (StringUtils.hasText(updateInfo.getPassword())){
            member.saveProfile(updateInfo.getName(), PassEncode.encode(updateInfo.getPassword()));

        } else {
            member.saveProfile(updateInfo.getName(), member.getPassword());
        }

        return member;
    }

    @Override
    public MemberDTO memberInfoByNum(Long num) {
        return memberCustomRepository.findByNum(num);
    }

    @Override
    public void saveAttach(Long num, MultipartFile attach) {
        MemberEntity member = memberRepository.findByNum(num);
        String filename = FileUpload.fileUpload(attach, 2);

        if (member.getProfileIMG()==null){
            member.saveProfileIMG(filename);

        }else {
            FileUpload.fileRemove(member.getProfileIMG(), 2);
            member.saveProfileIMG(filename);

        }
    }
}
