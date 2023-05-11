package kr.ac.kopo.ReadyToTravel.group;

import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import kr.ac.kopo.ReadyToTravel.entity.group.GroupEntity;
import kr.ac.kopo.ReadyToTravel.entity.group.GroupMembership;
import kr.ac.kopo.ReadyToTravel.entity.group.InviteEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;


@Service
public class GroupServiceImpl implements GroupService {
    final GroupRepository groupRepository;
    final GroupMembershipRepository groupMembershipRepository;

    final InviteUrlRepository inviteUrlRepository;

    public GroupServiceImpl(GroupRepository groupRepository, GroupMembershipRepository groupMembershipRepository, InviteUrlRepository inviteUrlRepository) {
        this.groupRepository = groupRepository;
        this.groupMembershipRepository = groupMembershipRepository;
        this.inviteUrlRepository = inviteUrlRepository;
    }

    @Override
    public void createGroup(Long planNum, Long leaderNum, String planName) {

        GroupEntity groupEntity = GroupEntity.builder()
                .createDate(new Date())
                .modifiedDate(new Date())
                .name(planName)
                .build();

        Long groupNum = groupRepository.save(groupEntity).getGroupNum();

        groupMembershipRepository.save(GroupMembership.builder()
                .group(GroupEntity.builder().groupNum(groupNum).build())
                .member(MemberEntity.builder().num(leaderNum).build())
                .build());

    }

    @Override
    public void groupAddMember(Long memberNum, String inviteURL) {

        // 초대 URL로 그룹 번호 조회
        long groupNum = inviteUrlRepository.findByInviteURL(inviteURL).getNum();

        // 그룹 멤버십 엔티티 생성
        GroupMembership membership = GroupMembership.builder()
                .group(GroupEntity.builder().groupNum(groupNum).build())
                .member(MemberEntity.builder().num(memberNum).build())
                .build();

        // 그룹 멤버십 저장
        groupMembershipRepository.save(membership);
    }

    @Override
    public void removeMember(long memberNum) {

        groupMembershipRepository.deleteById(memberNum);
    }

    @Override
    public String createInviteUrl(long groupNum) {
        InviteEntity existingInvite = inviteUrlRepository.findByGroupEntity_GroupNum(groupNum);
        if (existingInvite != null) {

            return existingInvite.getInviteURL();

        } else {
            UUID uuid = UUID.randomUUID();
            String uuidStr = uuid.toString().replace("-", "");
            String randomURL = uuidStr.substring(0, 8);

            InviteEntity inviteEntity = InviteEntity.builder()
                    .inviteURL(randomURL)
                    .groupEntity(GroupEntity.builder().groupNum(groupNum).build())
                    .build();

            String inviteURL = inviteUrlRepository.save(inviteEntity).getInviteURL();

            return inviteURL;
        }
    }
}