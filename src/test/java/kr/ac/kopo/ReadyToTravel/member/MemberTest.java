package kr.ac.kopo.ReadyToTravel.member;

import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.util.PassEncode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MemberTest {
    @Autowired
    MemberController controller;

    @Autowired
    MemberService service;

    @Test
    @DisplayName("아이디 중복 체크")
    public void checkId() {
        String checkId = controller.checkId("admin");
        String dummyId = controller.checkId("dummy");

        System.out.println("admin 테스트 결과 : " + checkId);
        System.out.println("dummy 테스트 결과 : " + dummyId);
    }

    @Test
    @DisplayName("회원 가입 테스트")
    public void signUp() {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId("test");
        memberDTO.setPassword(PassEncode.encode("test"));
        memberDTO.setEmail("test@test.com");
        memberDTO.setSignupDate(new Date());
        memberDTO.setPhoneNum("01012345678");

        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "test".getBytes());

        controller.signUp(memberDTO, file);
    }

    @Test
    @DisplayName("회원 삭제 테스트")
    public void removeMember() {
        // 가짜 HttpServletRequest 객체 생성
        HttpServletRequest request = mock(HttpServletRequest.class);

        // 가짜 세션 객체 생성 및 memberDTO 객체 설정
        HttpSession session = mock(HttpSession.class);
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setNum(1L);
        when(session.getAttribute("memberDTO")).thenReturn(memberDTO);
        when(request.getSession()).thenReturn(session);

        // 서비스의 removeMember() 메소드 호출
        service.removeMember(1L);


    }
}
