
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

import java.util.Date;

@RequestMapping("/plan")
@Controller
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    private final MemberService memberService;

    private final GroupService groupService;


    @GetMapping("/viewPlan/{planNum}")
    public String viewPlan(@PathVariable Long planNum, @SessionAttribute MemberDTO memberDTO, Model model) {
        Long memberNum = memberDTO.getNum();
        model.addAttribute("member", memberNum);

        Long leaderNum = planService.findLeader(planNum);
        model.addAttribute("leader", leaderNum);

        return "/plan/viewPlan";
    }

    @GetMapping("/getMarker/{planNum}")
    @ResponseBody
    public PlanDTO getMarker(@PathVariable Long planNum) {
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
     * @param plan    (plan / List<lonlatdto> / memberNum)
     * @param memberDTO (member session)
     * @return
     */
    @PostMapping("/createPlan")
    @ResponseBody
    public Long createPlan(@RequestBody PlanDTO plan, @SessionAttribute(name = "memberDTO", required = false) MemberDTO memberDTO) {
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
    public void updatePlan(@PathVariable Long planNum, @RequestBody PlanDTO plan, @SessionAttribute(name = "memberDTO", required = false) MemberDTO memberDTO) {
        plan.setNum(planNum);
        plan.setLeaderNum(memberDTO.getNum());

        planService.updatePlan(plan);
    }

    @GetMapping("/removePlan/{planNum}")
    public String removePlan(@PathVariable Long planNum) {

        planService.removePlan(planNum);

        return "redirect:/";
    }
}
