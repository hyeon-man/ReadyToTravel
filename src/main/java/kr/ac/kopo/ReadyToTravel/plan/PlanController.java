package kr.ac.kopo.ReadyToTravel.plan;

import kr.ac.kopo.ReadyToTravel.dto.PlanDTO;
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
    public String makePlan(@Valid PlanDTO plan){
        service.makePlan(plan);


        //todo 솔로 여행이면 그룹생성 x , 이외의 조건이면 그룹생성 컨트롤러로 연결
        return "";
    }
}
