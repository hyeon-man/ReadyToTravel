package kr.ac.kopo.ReadyToTravel.plan;

import kr.ac.kopo.ReadyToTravel.dto.plan.LonLatDTO;
import kr.ac.kopo.ReadyToTravel.dto.plan.PlanDTO;
import kr.ac.kopo.ReadyToTravel.entity.plan.PlanEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/plan")
@Controller
public class PlanController {
    final PlanService service;

    public PlanController(PlanService service) {
        this.service = service;
    }

    /**
     * @return plan/makePlan 페이지를 반환합니다.
     */
    @GetMapping("/createPlan")
    public String createPlan() {
        return "plan/createPlan";
    }

    /**
     * 사용자가 입력한 Plan 정보를 받아 저장합니다.
     *
     * @param plan
     * @return
     */
    @PostMapping("/createPlan")
    public String createPlan(@Valid PlanDTO plan) {
        service.makePlan(plan);

        return "";
    }

    @GetMapping("/updatePlan/{num}")
    public String updatePlan(@PathVariable Long num, Model model) {
        PlanEntity entity = service.getItem(num);

        model.addAttribute("item", entity);

        return "";
    }

    @PostMapping("/updatePlan/{num}")
    public String updatePlan(@PathVariable Long num, @Valid PlanDTO plan) {
        plan.setNum(num);

        service.updatePlan(plan);

        return "";
    }

    @RequestMapping("/removePlan/{num}")
    public String removePlan(@PathVariable Long num) {
        service.removePlan(num);
        return "";
    }
}
