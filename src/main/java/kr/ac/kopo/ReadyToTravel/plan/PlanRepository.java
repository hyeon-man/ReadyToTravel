package kr.ac.kopo.ReadyToTravel.plan;

import kr.ac.kopo.ReadyToTravel.entity.PlanEntity;
import kr.ac.kopo.ReadyToTravel.model.PlanVo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<PlanEntity, Long> {
}
