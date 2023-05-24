package kr.ac.kopo.ReadyToTravel.member;

import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import kr.ac.kopo.ReadyToTravel.util.PassEncode;
import kr.ac.kopo.ReadyToTravel.util.RedisUtil;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final JavaMailSender javaMailSender;
    private final MailService mailService;


    private final RedisUtil redisUtil;
    public MemberServiceImpl(MemberRepository memberRepository, JavaMailSender javaMailSender, MailService mailService, RedisUtil redisUtil) {
        this.memberRepository = memberRepository;
        this.javaMailSender = javaMailSender;
        this.mailService = mailService;
        this.redisUtil = redisUtil;
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
            Long validationLifetime = 300000l; //30만ms = 5분

            mailService.sendMailForEmail(email, uuid);

            redisUtil.setDataExpire(uuid, email, validationLifetime);
            return true;

        }else{
            System.out.println("존재하는 이메일 입니다");
            return false;
        }

    }

    @Override
    public boolean validateCode(String email, String mailValidateKey) {
        if (email == redisUtil.getData(mailValidateKey)){
            redisUtil.deleteData(mailValidateKey);
            return true;
        } else {
            return false;
        }
    }

}
