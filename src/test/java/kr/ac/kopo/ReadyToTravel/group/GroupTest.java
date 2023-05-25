package kr.ac.kopo.ReadyToTravel.group;

import com.icegreen.greenmail.util.GreenMail;
import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import kr.ac.kopo.ReadyToTravel.util.MailConfig;
import kr.ac.kopo.ReadyToTravel.util.PassEncode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GroupTest {
    @Autowired
    GroupCustomRepository groupCustomRepository;

    @Test
    @DisplayName(" QueryDSL test ")
    public void QueryDsl() {

        System.out.println("groupCustomRepository.findAllGroup() = " + groupCustomRepository.findAllGroup());
    }

    @Test
    @DisplayName("QueryDSL  -> GroupInMember")
    public void QueryDSL() {
        List<MemberEntity> list = groupCustomRepository.GroupInMember(1L);

        System.out.println(list);
    }
}


