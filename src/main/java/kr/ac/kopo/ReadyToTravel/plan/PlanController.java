package kr.ac.kopo.ReadyToTravel.plan;

import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.dto.plan.LonLatDTO;
import kr.ac.kopo.ReadyToTravel.dto.plan.PlanDTO;
import kr.ac.kopo.ReadyToTravel.entity.plan.PlanEntity;
import kr.ac.kopo.ReadyToTravel.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/plan")
@Controller
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    private final MemberService memberService;

    /**
     * @return plan/makePlan 페이지를 반환합니다.
     */
    @GetMapping("/createPlan")
    public String createPlan() {
        return "plan/createPlan";
    }

    /**
     *
     * @param plan (plan / List<lonlatdto> / memberNum)
     * @param request (member session)
     * @return (ResponseBody OK : planPage / FAIL : Login Modal
     */
    // TODO front Ajax
    @PostMapping("/createPlan")
    @ResponseBody
    public String createPlan(@Valid PlanDTO plan, HttpServletRequest request) {

        HttpSession session = request.getSession();

        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");

        if (memberDTO != null) {
            plan.setMemberNum(memberDTO.getNum());

            planService.makePlan(plan);
            return "OK";
        }
        else
            return "FAIL";
    }

    @GetMapping("/updatePlan/{num}")
    public String updatePlan(@PathVariable Long num, Model model) {
        PlanEntity entity = planService.getItem(num);

        model.addAttribute("item", entity);

        return "";
    }

    @PostMapping("/updatePlan/{num}")
    public String updatePlan(@PathVariable Long num, @Valid PlanDTO plan) {
        plan.setNum(num);

        planService.updatePlan(plan);

        return "";
    }

    @RequestMapping("/removePlan/{num}")
    public String removePlan(@PathVariable Long num) {
        planService.removePlan(num);
        return "";
    }
}
