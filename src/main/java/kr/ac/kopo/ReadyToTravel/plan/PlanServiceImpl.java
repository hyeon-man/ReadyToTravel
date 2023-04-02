package kr.ac.kopo.ReadyToTravel.plan;

import kr.ac.kopo.ReadyToTravel.entity.PlanEntity;
import kr.ac.kopo.ReadyToTravel.vo.PlanVo;
import org.springframework.stereotype.Service;

@Service
public class PlanServiceImpl implements PlanService {
    final PlanRepository planRepository;

    public PlanServiceImpl(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }


    @Override
    public void makePlan(PlanVo planVo) {
        // TODO: 2023-03-29 PlanVo 로 PlanEntity 생성 후 DB저장

        PlanEntity entity = new PlanEntity();

        planRepository.save(entity);
    }
}
