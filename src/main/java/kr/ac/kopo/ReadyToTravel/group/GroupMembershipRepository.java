package kr.ac.kopo.ReadyToTravel.group;

import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import kr.ac.kopo.ReadyToTravel.entity.group.GroupMembership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupMembershipRepository extends JpaRepository<GroupMembership, Long> {
    List<MemberEntity> findAllByNum(long groupNum);

    void deleteByGroup_GroupNumAndMember_Num(long groupNum, long memberNum);

    GroupMembership findByGroup_GroupNumAndMember_Num(long groupNum, long memberNum);
}
