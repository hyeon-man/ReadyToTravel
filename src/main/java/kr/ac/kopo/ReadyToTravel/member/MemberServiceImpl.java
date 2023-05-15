package kr.ac.kopo.ReadyToTravel.member;

import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import kr.ac.kopo.ReadyToTravel.util.PassEncode;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final JavaMailSender javaMailSender;

    public MemberServiceImpl(MemberRepository memberRepository, JavaMailSender javaMailSender) {
        this.memberRepository = memberRepository;
        this.javaMailSender = javaMailSender;
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

        MemberDTO loginMember = memberDTO.convertToMemberDto(memberInfo);

        return loginMember;
    }


    @Override
    public boolean initPass(String email) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        uuid = uuid.substring(0, 8);

        Optional<MemberEntity> findMember = memberRepository.findByEmail(email);
        if (!findMember.isPresent()) {
            return false;
        }

        MemberEntity originMember = findMember.get();
        originMember.setPassword(PassEncode.encode(uuid));

        MailService mailService = new MailService(javaMailSender);
        mailService.sendMail(originMember.getEmail(), uuid);

        return true;
        //else는 뭐라 해야하지.... 고민 해봐야함 optional 말고 throw를 해야하나...?
        /*try {
            MemberEntity member = optionalMember.get(); // 여기서 nullpoint exception 발생하는가?
            String newPassword = GenerateTemporaryPassword.generateTemporaryPassword();
            member.setPassword(newPassword);
            repository.save(member);
            return member;
        } catch (NullPointerException n){

            return new MemberEntity();
        }*/
    }
}