package kr.ac.kopo.ReadyToTravel.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
public class MailService {

    private final JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    private MimeMessage createMessage(String email) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        mimeMessage.setFrom(new InternetAddress("readytotravel2304@naver.com"));
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
        mimeMessage.setSubject("비밀번호 변경");
/*
        mimeMessage.setText(????); // service에서 member엔티티에 저장한 비밀번호를 넣어야함.
*/

        return mimeMessage;
    }

    public void sendMail(String email) {
        try {
            MimeMessage message = createMessage(email);
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
    }