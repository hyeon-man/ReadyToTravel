package kr.ac.kopo.ReadyToTravel.member;

import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import kr.ac.kopo.ReadyToTravel.util.PassEncode;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.lang.reflect.Member;
import java.util.Optional;
import java.util.Random;

@Service
public class MemberServiceImpl implements MemberService {
    final MemberRepository repository;
    final JavaMailSender javaMailSender;
    public MemberServiceImpl(MemberRepository repository, JavaMailSender javaMailSender) {
        this.repository = repository;
        this.javaMailSender = javaMailSender;
    }


    @Override
    public String checkId(String id) {
        MemberEntity entity = repository.findAllByMemberId(id);
        if (entity == null) {
            return "사용 가능";
        } else {
            return "사용 불가";
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
        repository.save(entity);
    }

    @Override
    public void removeMember(Long num) {
        Optional<MemberEntity> optionalMemberEntity = repository.findById(num);

        if (optionalMemberEntity.isPresent()) {
            repository.deleteById(num);
            System.out.println("지움");
        } else {
            System.out.println(num + "에 해당하는 회원이 없습니다");
        }
    }

    @Override
    public boolean login(MemberDTO memberDTO) {
        MemberEntity memberEntity = memberDTO.convertToEntity(memberDTO);

        String id = memberEntity.getMemberId();
        String pass = PassEncode.encode(memberEntity.getPassword());

        return repository.existsByMemberIdAndPassword(id, pass);

    }

    // 10자리 랜덤 초기화 비밀번호 생성
    private String generateTemporaryPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 10;
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    @Override
    public void initPassword(String email) throws MessagingException {
        Optional<MemberEntity> optionalMember = repository.findByEmail(email);

        if (optionalMember.isPresent()) {
            MemberEntity member = optionalMember.get();
            String newPassword = generateTemporaryPassword();
            member.setPassword(newPassword);
            repository.save(member);
            sendMail(email, "ReadyToTravel 비밀번호 초기화 안내", "새로운 비밀번호는 " + newPassword + " 입니다.");
        }
    }

    //메일 보내기
    public void sendMail(String email, String title, String text) throws MessagingException {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(email);
            helper.setSubject(title);
            helper.setText(text, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new MessagingException("Failed to send email.", e);
        }
    }

}
