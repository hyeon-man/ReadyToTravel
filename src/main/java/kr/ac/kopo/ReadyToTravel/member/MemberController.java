package kr.ac.kopo.ReadyToTravel.member;

import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.util.FileUpload;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @RequestMapping("/signup")
    public String signUp(MemberDTO memberDTO, @RequestParam("file") MultipartFile file) {

        String fileName = FileUpload.fileUpload(file);
        memberDTO.setProfileIMG(fileName);

        service.singUp(memberDTO);

        return "index";
    }

    @RequestMapping("/checkId/{id}")
    @ResponseBody
    public String checkId(@PathVariable String id) {

        return service.checkId(id);
    }

    @RequestMapping("/removerMember")
    @ResponseBody
    public void delete(HttpServletRequest request) {
        MemberDTO dto = (MemberDTO) request.getSession().getAttribute("memberDTO");
        Long num = dto.getNum();

        service.removeMember(num);

    }

    @GetMapping("/findPassword")
    public String findPassword() {

        return "member/findPassword";
    }

    @PostMapping("/findPassword")
    public String findPassword(String email) throws MessagingException {

        if (service.initPass(email)) {
            return "redirect:../";
        } else {
            throw new RuntimeException("메일 전송 실패함");
        }
    }

    @PostMapping("/login")
    public String login(MemberDTO memberDTO,HttpSession session) {

        boolean isValid = service.login(memberDTO);

        if (service.login(memberDTO)) {

            return "index";
        } else {
            return "member/login";
        }
    }
}
