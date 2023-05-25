package kr.ac.kopo.ReadyToTravel.member;

import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/checkId/{id}")
    @ResponseBody
    public String checkId(@PathVariable String id) {
        if(service.checkId(id)){
            return "OK";

        } else {

            return "FAIL";
        }
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
    public String initPassword(String id, String email) throws MessagingException {

        if (service.initPass(id, email)) {
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

        System.out.println("memberDTO = " + memberDTO.getMemberId());
        System.out.println("memberDTO = " + memberDTO.getPassword());

        MemberDTO login = service.login(memberDTO);

        if (login != null) {
            System.out.println("login ! ===== " + login);
            session.setAttribute("memberDTO", login);

            return  "redirect:/";
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

    @PostMapping("/signup")
    public String signUp(MemberDTO memberDTO) {
        System.out.println(memberDTO);
        service.singUp(memberDTO);

        return "index";
    }

    @ResponseBody
    @GetMapping("/checkEmail/{email}")
    public String sendEmailCode(@PathVariable String email){
        if(service.sendEmailCode(email)){

            return "sendMailOK";
        }else{
            return "senMailFail" ;
        }
    }
    @ResponseBody
    @RequestMapping("/validateCode")
    public String validateCode(String email, String mailValidateCode){
        if(service.validateCode(email, mailValidateCode)){
            return "emailValidOK";
        }
        return "emailValidFAIL";
    }
}
