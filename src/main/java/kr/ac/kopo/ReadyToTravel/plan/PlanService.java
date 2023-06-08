package kr.ac.kopo.ReadyToTravel.plan;

import kr.ac.kopo.ReadyToTravel.dto.plan.PlanDTO;

import java.util.List;

public interface PlanService {
    Long createPlan(PlanDTO plan);

    Long updatePlan(PlanDTO plan);

    void removePlan(Long num);

    PlanDTO viewPlan(long planNum);

    PlanDTO planInform(Long num);

    List<PlanDTO> smallPlanInfo(long memberNum);
}
