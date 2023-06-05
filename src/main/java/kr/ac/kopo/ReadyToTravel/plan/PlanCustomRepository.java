package kr.ac.kopo.ReadyToTravel.plan;

import kr.ac.kopo.ReadyToTravel.dto.plan.PlanDTO;

public interface PlanCustomRepository {
    PlanDTO findByNum(long planNum);
}
