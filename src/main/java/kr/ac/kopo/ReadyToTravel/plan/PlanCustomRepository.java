package kr.ac.kopo.ReadyToTravel.plan;

import kr.ac.kopo.ReadyToTravel.dto.plan.PlanDTO;

import java.util.List;

public interface PlanCustomRepository {
    PlanDTO findByNum(long planNum);

    PlanDTO memberAndPlanInfo(Long num);

    List<PlanDTO> smallPlanInfo(long memberNum);

    List<PlanDTO> myPlanList(Long num);
}
