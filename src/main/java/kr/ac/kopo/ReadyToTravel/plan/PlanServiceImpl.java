package kr.ac.kopo.ReadyToTravel.plan;

import kr.ac.kopo.ReadyToTravel.dto.plan.LonLatDTO;
import kr.ac.kopo.ReadyToTravel.dto.plan.PlanDTO;
import kr.ac.kopo.ReadyToTravel.entity.plan.LonLatEntity;
import kr.ac.kopo.ReadyToTravel.entity.plan.PlanEntity;
import kr.ac.kopo.ReadyToTravel.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private final LonLatRepository lonLatRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long createPlan(PlanDTO plan) {
        PlanEntity planConvertToEntity = plan.convertToEntity(plan, plan.getLeaderNum());

        PlanEntity planEntity = planRepository.save(planConvertToEntity);
        System.out.println("planEntity = " + planEntity);

        List<LonLatEntity> lonLatEntities = new ArrayList<>();

        for (int i = 0; i < plan.getLonLatDTOList().size(); i++) {
            LonLatEntity entityList = LonLatDTO.convertToEntity(plan.getLonLatDTOList().get(i), planEntity.getNum());
            lonLatEntities.add(entityList);
        }
        lonLatRepository.saveAll(lonLatEntities);

        return planEntity.getNum();
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
