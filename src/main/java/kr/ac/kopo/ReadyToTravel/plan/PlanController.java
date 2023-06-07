
package kr.ac.kopo.ReadyToTravel.plan;

import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.dto.plan.PlanDTO;
import kr.ac.kopo.ReadyToTravel.group.GroupService;
import kr.ac.kopo.ReadyToTravel.member.MemberService;
import kr.ac.kopo.ReadyToTravel.plan.travelType.TravelType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;

@RequestMapping("/plan")
@Controller
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    private final MemberService memberService;

    private final GroupService groupService;


    @GetMapping("/viewPlan/{planNum}")

    public String viewPlan(@PathVariable long planNum) {
        return "/plan/viewPlan";
    }

    @GetMapping("/getMarker/{planNum}")
    @ResponseBody
    public PlanDTO getMarker(@PathVariable long planNum) {
        PlanDTO view = planService.viewPlan(planNum);

        return view;
    }
    /**
     * @return plan/makePlan 페이지를 반환합니다.
     */
    @GetMapping("/createPlan")
    public String createPlan() {

        return "/plan/createPlan";
    }

/**
     *
     * @param plan (plan / List<lonlatdto> / memberNum)
     * @param request (member session)
     * @return
 */
    @PostMapping("/createPlan")
    @ResponseBody
    public Long createPlan(@RequestBody PlanDTO plan, HttpServletRequest request) {

        HttpSession session = request.getSession();

        MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");

            plan.setCreateDate(new Date());
            plan.setLeaderNum(memberDTO.getNum());

            Long planNum = planService.createPlan(plan);

            if (plan.getType() != TravelType.SOLO) {
                groupService.createGroup(planNum, plan.getLeaderNum(), plan.getName());
            }
            return planNum;
    }

    @GetMapping("/updatePlan/{planNum}")
    public String updatePlan(@PathVariable Long planNum, Model model) {
        return "/plan/updatePlan";
    }

    @PostMapping("/updatePlan/{planNum}")
    public Long updatePlan(@PathVariable Long planNum, @Valid PlanDTO plan) {
        plan.setNum(planNum);

        planService.updatePlan(plan);

        return planNum;
    }

    @RequestMapping("/removePlan/{planNum}")
    public String removePlan(@PathVariable Long planNum) {
        planService.removePlan(planNum);
        return "";
    }
}
