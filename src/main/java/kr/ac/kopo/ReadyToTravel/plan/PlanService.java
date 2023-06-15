package kr.ac.kopo.ReadyToTravel.plan;

import kr.ac.kopo.ReadyToTravel.dto.plan.PlanDTO;

import java.util.List;

public interface PlanService {
    PlanDTO planInform(Long num);

    List<PlanDTO> smallPlanInfo(long memberNum);

    Long createPlan(PlanDTO plan);

    void updatePlan(PlanDTO plan);

    void removePlan(Long planNum);

    PlanDTO viewPlan(Long planNum);
}
