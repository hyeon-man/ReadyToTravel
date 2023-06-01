package kr.ac.kopo.ReadyToTravel.member;

import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import kr.ac.kopo.ReadyToTravel.util.CacheConfig;
import kr.ac.kopo.ReadyToTravel.util.PassEncode;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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



    public MemberServiceImpl(MemberRepository memberRepository, JavaMailSender javaMailSender, MailService mailService, CacheConfig cacheConfig) {
        this.memberRepository = memberRepository;
        this.javaMailSender = javaMailSender;
        this.mailService = mailService;
        this.cacheConfig = cacheConfig;
    }

    @Override
    public boolean checkId(String id) {
        MemberEntity entity = memberRepository.findAllByMemberId(id);
        if (entity == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void singUp(MemberDTO memberDTO) {
        memberDTO.setSignupDate(new Date());

        // Convert DTO to Entity
        MemberEntity entity = memberDTO.convertToEntity(memberDTO);

        // Encode password to SHA-512
        String encodedPassword = PassEncode.encode(entity.getPassword());
        entity.setPassword(encodedPassword);

        // Save entity
        memberRepository.save(entity);
    }

    @Override
    public void removeMember(Long num) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(num);

        if (optionalMemberEntity.isPresent()) {
            memberRepository.deleteById(num);
            System.out.println("지움");
        } else {
            System.out.println(num + "에 해당하는 회원이 없습니다");
        }
    }

    @Override
    public MemberDTO login(MemberDTO memberDTO) {

        MemberEntity memberEntity = memberDTO.convertToEntity(memberDTO);

        String id = memberEntity.getMemberId();
        String pass = PassEncode.encode(memberEntity.getPassword());

        MemberEntity memberInfo = memberRepository.findByMemberIdAndPassword(id, pass);

        try{
            MemberDTO loginMember = memberDTO.convertToMemberDto(memberInfo);
            return loginMember;
        } catch (NullPointerException ne){

            return null;
        }

        }


    @Override
    public boolean initPass(String id, String email) {

        Optional<MemberEntity> findMember = memberRepository.findByMemberIdAndEmail(id, email);
        if (!findMember.isPresent()) {
            return false;
        }

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        uuid = uuid.substring(0, 8);

        MemberEntity originMember = findMember.get();
        originMember.setPassword(PassEncode.encode(uuid));

        MailService mailService = new MailService(javaMailSender);
        mailService.sendMailForPass(originMember.getEmail(), uuid);

        return true;

    }
    @Override
    public boolean sendEmailCode(String email){
        Optional<MemberEntity> findEmail = memberRepository.findByEmail(email);
        if(!findEmail.isPresent()) {
            System.out.println("이메일이 존재하지 않습니다.");
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            uuid = uuid.substring(0, 8);
            mailService.sendMailForEmail(email, uuid);
            cacheConfig.putValue(email, uuid);
            return true;

        }else{
            System.out.println("존재하는 이메일 입니다");
            return false;
        }

    }
    @Override
    public boolean validateCode(String email, String mailValidateKey) {
        if (cacheConfig.getValue(email) == null || !cacheConfig.getValue(email).equals(mailValidateKey)){
            return false;
            } else {
            System.out.println("good");
            return true;
        }
    }

    @Override
    public void update(MemberDTO memberDTO) {
        MemberEntity memberEntity = memberRepository.findById(memberDTO.getNum())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 멤버"));

        memberEntity.updateMember(memberDTO);
    }
}
