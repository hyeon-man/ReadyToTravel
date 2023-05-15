package kr.ac.kopo.ReadyToTravel.plan;

import kr.ac.kopo.ReadyToTravel.dto.plan.PlanDTO;
import kr.ac.kopo.ReadyToTravel.entity.plan.PlanEntity;

public interface PlanService {
    Long createPlan(PlanDTO plan);

    PlanEntity getItem(Long num);

    void updatePlan(PlanDTO plan);

    void removePlan(Long num);
}
