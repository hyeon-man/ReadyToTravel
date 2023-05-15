package kr.ac.kopo.ReadyToTravel.member;

import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.util.FileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemberController {
    final String path = "member/";

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
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

    @GetMapping("/initPassword")
    public String initPassword() {

        return path + "initPassword";
    }

    @ResponseBody
    @PostMapping("/initPassword")
    public String initPassword(String email) throws MessagingException {

        if (service.initPass(email)) {
            return "SUCCESS";
        } else {
            return "FAIL";
        }
    }
    @GetMapping("/login")
    public String login() {
        return path + "login";
    }
    @PostMapping("/login")
    public String login(MemberDTO memberDTO, HttpSession session) {

        if (service.login(memberDTO)) {
            memberDTO.setPassword(null);

            session.setAttribute("memberDTO", memberDTO);

            return "index";
        } else {
            return path + "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("memberDTO");

        return "redirect:/";
    }
    @GetMapping("/signup")
    public String signup() {
        return path + "signup";
    }

    @RequestMapping("/signup")
    public String signUp(MemberDTO memberDTO, @RequestParam("file") MultipartFile file) {

        String fileName = FileUpload.fileUpload(file);
        memberDTO.setProfileIMG(fileName);

        service.singUp(memberDTO);

        return "index";
    }
}
