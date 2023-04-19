package kr.ac.kopo.ReadyToTravel.plan;

import kr.ac.kopo.ReadyToTravel.dto.plan.LonLatDTO;
import kr.ac.kopo.ReadyToTravel.dto.plan.PlanDTO;
import kr.ac.kopo.ReadyToTravel.entity.plan.PlanEntity;

public interface PlanService {
    void makePlan(PlanDTO plan);

    PlanEntity getItem(Long num);

    void updatePlan(PlanDTO plan);

    void removePlan(Long num);
}
