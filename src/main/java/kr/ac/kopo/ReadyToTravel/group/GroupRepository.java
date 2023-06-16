package kr.ac.kopo.ReadyToTravel.group;

import kr.ac.kopo.ReadyToTravel.entity.group.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

    GroupEntity findByPlanNum(long planNum);

    void deleteByPlanNum(long planNum);

}
