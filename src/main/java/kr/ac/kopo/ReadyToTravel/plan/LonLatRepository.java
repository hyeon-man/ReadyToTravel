package kr.ac.kopo.ReadyToTravel.plan;

import kr.ac.kopo.ReadyToTravel.entity.plan.LonLatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LonLatRepository extends JpaRepository<LonLatEntity, Long> {
    List<LonLatEntity> findAllByPlanEntityNum(long planNum);
}
