
package kr.ac.kopo.ReadyToTravel.plan;

import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.dto.plan.PlanDTO;
import kr.ac.kopo.ReadyToTravel.entity.plan.PlanEntity;
import kr.ac.kopo.ReadyToTravel.group.GroupController;
import kr.ac.kopo.ReadyToTravel.group.GroupService;
import kr.ac.kopo.ReadyToTravel.member.MemberService;
import kr.ac.kopo.ReadyToTravel.plan.travelType.TravelType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RequestMapping("/plan")
@Controller
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    private final MemberService memberService;

    private final GroupService groupService;


    /**
     * @return plan/makePlan 페이지를 반환합니다.
     */
    @GetMapping("/calendars")
    public String calendars() {
        return "plan/calendars";
    }

    @GetMapping("/createPlan")
    public String createPlan() {

        return "plan/createPlan";
    }

/**
     *
     * @param plan (plan / List<lonlatdto> / memberNum)
     * @param request (member session)
     * @return
 */

// TODO lonlatDTO도 controller에서 받기 PlanDTO에 있는 LonlatList 지우기
    @ResponseBody
    @PostMapping("/createPlan")
    public String createPlan(PlanDTO plan, HttpServletRequest request) {

        HttpSession session = request.getSession();

        MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");

        if (memberDTO != null) {
            plan.setLeaderNum(memberDTO.getNum());
            Long planNum = planService.createPlan(plan);

            if (plan.getPlanType() != TravelType.SOLO) {
                groupService.createGroup(planNum, plan.getLeaderNum(), plan.getName());
            }
//            return "redirect:/plan/detail/" + planNum;
            return "OK";
        }
        //TODO 멤버 세션이 없을 경우 return
//        return "member/login";
        return "FAIL";
    }

    @GetMapping("/updatePlan/{num}")
    public String updatePlan(@PathVariable Long num, Model model) {
        PlanEntity entity = planService.getItem(num);

        //todo 솔로 여행이면 그룹생성 x , 이외의 조건이면 그룹생성 컨트롤러로 연결
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

