package kr.ac.kopo.ReadyToTravel.member;

import kr.ac.kopo.ReadyToTravel.board.BoardService;
import kr.ac.kopo.ReadyToTravel.dto.BoardDTO;
import kr.ac.kopo.ReadyToTravel.dto.GroupDTO;
import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.dto.plan.PlanDTO;
import kr.ac.kopo.ReadyToTravel.group.GroupService;
import kr.ac.kopo.ReadyToTravel.plan.PlanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {
    private final MemberService service;
    private final BoardService boardService;
    private final PlanService planService;

    private final GroupService groupService;

    public MemberController(MemberService service, BoardService boardService, PlanService planService, GroupService groupService) {
        this.service = service;
        this.boardService = boardService;
        this.planService = planService;
        this.groupService = groupService;
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
    public void delete(@PathVariable long memberNum, HttpSession session) {

        service.removeMember(memberNum);

        session.removeAttribute("memberDTO");
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
        long memberNum = memberDTO.getNum();
        MemberDTO member = service.memberInfoByNum(memberNum);
        model.addAttribute("memberDTO", member);

//        List<PlanDTO> plans = planService.smallPlanInfo(memberNum);


        return "member/profile";
    }


    @PostMapping("/profile/update")
    public String profileUpdate(@SessionAttribute MemberDTO memberDTO, MemberDTO updateInfo) {

        if (updateInfo.getProfileFile().getSize() > 0) {
            service.profileUpdate(memberDTO.getNum(), updateInfo);
            service.saveAttach(memberDTO.getNum(), updateInfo.getProfileFile());
        } else
            service.profileUpdate(memberDTO.getNum(), updateInfo);

        return "redirect:/member/profile";
    }

    @ResponseBody
    @GetMapping("/profile/boardList")
    public List<BoardDTO> boardList(@SessionAttribute MemberDTO memberDTO){
        return boardService.myBoardList(memberDTO.getNum());
    }

    @ResponseBody
    @GetMapping("/profile/groupList")
    public GroupDTO groupList(@SessionAttribute MemberDTO memberDTO) {
        return groupService.myGroupList(memberDTO.getNum());
    }

    @GetMapping("/profile/removeMemberInGroup/{groupNum}")
    @ResponseBody
    public String removeMemberInGroup(@PathVariable Long groupNum, @RequestParam Long memberNum) {
        System.out.println("요청받은 그룹번호: " + groupNum + ", 요청받은 멤버 번호: " + memberNum);
         groupService.removeMember(groupNum, memberNum);

        return "deleteSucces";
    }


    @ResponseBody
    @GetMapping("/profile/calander")
    public PlanDTO calenderPlanInfo(@SessionAttribute MemberDTO memberDTO){
        return planService.findPlan(memberDTO.getNum());
    }
}
