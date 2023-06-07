package kr.ac.kopo.ReadyToTravel.member;

import kr.ac.kopo.ReadyToTravel.board.BoardService;
import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.plan.PlanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemberController {
    private final MemberService service;
    private final BoardService boardService;
    private final PlanService planService;

    public MemberController(MemberService service, BoardService boardService, PlanService planService) {
        this.service = service;
        this.boardService = boardService;
        this.planService = planService;
    }

    @GetMapping("/checkId/{id}")
    @ResponseBody
    public String checkId(@PathVariable String id) {
        if (service.checkId(id)) {
            return "OK";
        } else {
            return "FAIL";
        }
    }

    @GetMapping("/removerMember/{memberNum}")
    public void delete(@PathVariable long memberNum) {

        service.removeMember(memberNum);
    }

    @GetMapping("/initPassword")
    public String initPassword() {

        return "member/initPassword";
    }

    @ResponseBody
    @PostMapping("/initPassword")
    public String initPassword(String memberId, String email) throws MessagingException {
        if (service.initPass(memberId, email)) {
            return "SUCCESS";
        } else {
            return "FAIL";
        }
    }

    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @PostMapping("/login")
    public String login(MemberDTO memberDTO, HttpSession session) {

        MemberDTO member = service.login(memberDTO);

        if (member == null) {
            System.out.println("회원 정보가 없습니다");
            return "redirect:/member/login";
        } else {
            session.setAttribute("memberDTO", member);
            String targetUrl = (String) session.getAttribute("target_url");
            session.removeAttribute("target_url");
            if (targetUrl == null) {
                return "redirect:/";
            } else {
                return "redirect:" + targetUrl;
            }
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("memberDTO");

        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signup() {
        return "member/signup";
    }

    @PostMapping("/signup")
    public String signUp(MemberDTO memberDTO) {
        System.out.println(memberDTO);
        service.singUp(memberDTO);
        return "redirect:/member/login";
    }

    @ResponseBody
    @GetMapping("/checkEmail/{email}")
    public String sendEmailCode(@PathVariable String email) {
        if (service.sendEmailCode(email)) {
            return "sendMailOK";
        } else {
            return "senMailFail";
        }
    }

    @ResponseBody
    @RequestMapping("/validateCode")
    public String validateCode(String email, String mailValidateCode) {
        if (service.validateCode(email, mailValidateCode)) {
            return "emailValidOK";
        } else {
            return "emailValidFAIL";
        }
    }

    @GetMapping("/profile")
    public String myPage(Model model, @SessionAttribute MemberDTO memberDTO) {
        MemberDTO member = service.memberInfoByNum(memberDTO.getNum());
        model.addAttribute("memberDTO", member);


        return "member/profile";
    }


    @PostMapping("/profile/update")
    public String profileUpdate(@SessionAttribute MemberDTO memberDTO, MemberDTO updateInfo) {
        // TODO: 2023-06-08 Attach null 검증 안됨 해결해야함. 시도해본것: profileIMG검증(받지 않아서 파일 있든 없든 null), profileFile검증(있든 없든 null이 안됨)

        if (updateInfo.getProfileIMG() != null) {
            service.profileUpdate(memberDTO.getNum(), updateInfo);
            service.saveAttach(memberDTO.getNum(), updateInfo.getProfileFile());
        } else
            service.profileUpdate(memberDTO.getNum(), updateInfo);

        return "redirect:/member/profile";
    }

}
