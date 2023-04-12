package kr.ac.kopo.ReadyToTravel.plan.group;

import kr.ac.kopo.ReadyToTravel.entity.group.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
}
