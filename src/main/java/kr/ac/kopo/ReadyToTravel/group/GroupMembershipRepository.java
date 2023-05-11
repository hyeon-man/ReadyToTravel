package kr.ac.kopo.ReadyToTravel.group;

import kr.ac.kopo.ReadyToTravel.entity.group.GroupMembership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupMembershipRepository extends JpaRepository<GroupMembership, Long> {
}
