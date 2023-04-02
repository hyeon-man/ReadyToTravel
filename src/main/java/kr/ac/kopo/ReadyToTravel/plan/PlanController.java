package kr.ac.kopo.ReadyToTravel.plan;

import kr.ac.kopo.ReadyToTravel.vo.PlanVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/plan")
@Controller
public class PlanController {
    final PlanService service;

    public PlanController(PlanService service) {
        this.service = service;
    }

    /**
     * plan/makePlan 페이지를 반환합니다.
     * @return
     */
    @GetMapping("/makePlan")
    public String makePlanPage(){

        return "plan/makePlan";
    }

    /**
     * 사용자가 입력한 Plan 정보를 받아 저장합니다.
     * @param plan
     * @return
     */
    @PostMapping("/makePlan")
    public String makePlan(@Valid PlanVo plan){
        service.makePlan(plan);

        // TODO: 2023-03-29 로직이 완성되면 그룹 페이지로 이동한다
        return "";
    }
}
