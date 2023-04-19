package kr.ac.kopo.ReadyToTravel.plan;

import kr.ac.kopo.ReadyToTravel.dto.plan.LonLatDTO;
import kr.ac.kopo.ReadyToTravel.entity.plan.PlanEntity;
import kr.ac.kopo.ReadyToTravel.dto.plan.PlanDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;

    @Override
    public void makePlan(PlanDTO plan) {
        PlanEntity entity = plan.convertToEntity(plan);

        planRepository.save(entity);
    }

    @Override
    public PlanEntity getItem(Long num) {
        return null;
    }

    @Override
    public void updatePlan(PlanDTO plan) {

    }

    @Override
    @Transactional
    public void removePlan(Long num) {
        planRepository.deleteById(num);
    }
}
