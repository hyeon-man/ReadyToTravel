package kr.ac.kopo.ReadyToTravel.plan.group;

import kr.ac.kopo.ReadyToTravel.dto.PlanDTO;
import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import kr.ac.kopo.ReadyToTravel.entity.PlanEntity;
import kr.ac.kopo.ReadyToTravel.entity.group.GroupEntity;
import kr.ac.kopo.ReadyToTravel.entity.group.GroupMembership;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class GroupServiceImpl implements GroupService {
    final GroupRepository groupRepository;
    final GroupMembershipRepository groupMembershipRepository;

    public GroupServiceImpl(GroupRepository groupRepository, GroupMembershipRepository groupMembershipRepository) {
        this.groupRepository = groupRepository;
        this.groupMembershipRepository = groupMembershipRepository;
    }

    @Override
    public void createGroup(Long planNum, Long leaderNum, String planName) {

        GroupEntity groupEntity = GroupEntity.builder()
                .createDate(new Date())
                .modifiedDate(new Date())
                .plan(PlanEntity.builder().num(planNum).build())
                .name(planName)
                .build();

        Long groupNum = groupRepository.save(groupEntity).getGroupNum();


        groupMembershipRepository.save(GroupMembership.builder()
                .group(GroupEntity.builder().groupNum(groupNum).build())
                .member(MemberEntity.builder().num(leaderNum).build())
                .build());

    }

    @Override
    public void removeGroup(Long groupNum) {
        groupRepository.deleteById(groupNum);
    }

    @Override
    public void groupAddMember(Long GroupNum, Long memberNum) {

    }
}
