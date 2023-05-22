package kr.ac.kopo.ReadyToTravel.group;

import kr.ac.kopo.ReadyToTravel.dto.GroupDTO;
import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import kr.ac.kopo.ReadyToTravel.entity.group.GroupEntity;
import kr.ac.kopo.ReadyToTravel.entity.group.GroupMembership;
import kr.ac.kopo.ReadyToTravel.entity.plan.PlanEntity;
import kr.ac.kopo.ReadyToTravel.member.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class GroupServiceImpl implements GroupService {
    final GroupRepository groupRepository;
    final GroupMembershipRepository groupMembershipRepository;
    final InviteUrlRepository inviteUrlRepository;
    private final MemberRepository memberRepository;

    private final GroupCustomRepository groupCustomRepository;

    public GroupServiceImpl(GroupRepository groupRepository, GroupMembershipRepository groupMembershipRepository, InviteUrlRepository inviteUrlRepository, MemberRepository memberRepository, GroupCustomRepository groupCustomRepository) {
        this.groupRepository = groupRepository;
        this.groupMembershipRepository = groupMembershipRepository;
        this.inviteUrlRepository = inviteUrlRepository;
        this.memberRepository = memberRepository;
        this.groupCustomRepository = groupCustomRepository;
    }

    @Override
    public void createGroup(Long planNum, Long leaderNum, String planName) {

        // 그룹 생성
        GroupEntity groupEntity = GroupEntity.builder()
                .createDate(new Date())
                .modifiedDate(new Date())
                .plan(PlanEntity.builder().num(planNum).build())
                .name(planName)
                .build();


        // GROUP 엔티티 저장
        GroupEntity save = groupRepository.save(groupEntity);

        // 그룹에 member 추가
        MemberEntity entity = memberRepository.findById(leaderNum)
                .orElseThrow(() -> new RuntimeException("member 정보가 없습니다"));

        groupMembershipRepository.save(GroupMembership.builder()
                .group(save)
                .members(entity)
                .build());

    }

    @Override
    public void groupAddMember(Long memberNum, String inviteURL) {

        // 초대 URL로 그룹 번호 조회
        long groupNum = inviteUrlRepository.findByInviteURL(inviteURL).getNum();

//        // 그룹 멤버십 엔티티 생성
//        GroupMembership membership = GroupMembership.builder()
//                .group(GroupEntity.builder().groupNum(groupNum).build())
//                .members(MemberEntity.builder().num(memberNum).build())
//                .build();

        GroupMembership membership = GroupMembership.builder().build();

        // 그룹 멤버십 저장
        groupMembershipRepository.save(membership);
    }

    @Override
    public void removeMember(long memberNum) {

        groupMembershipRepository.deleteById(memberNum);
    }

    @Override
    public void updateGroup(GroupDTO group) {

        GroupEntity entity = GroupEntity
                .builder()
                .name(group.getPlanName())
                .modifiedDate(group.getModifiedDate())
                .build();


        groupRepository.save(entity);
    }

    @Override
    public GroupDTO item(long groupNum) {
        GroupEntity groupEntity = groupRepository.findById(groupNum)
                .orElseThrow(() -> new NullPointerException("그룹 번호로 조회한 결과가 없습니다."));

        GroupDTO groupDto = new GroupDTO();
        groupDto.convertToDto(groupEntity);

        List<MemberEntity> memberEntities = groupMembershipRepository.findAllByNum(groupNum);
        System.out.println(memberEntities.size());
        return groupDto;
    }

    @Override
    public List<MemberDTO> groupInMember(long groupNum) {
        List<MemberEntity> memberList = groupCustomRepository.GroupInMember(groupNum);

        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (MemberEntity member : memberList) {
            memberDTOList.add(MemberDTO.builder()
                    .memberId(member.getMemberId())
                    .num(member.getNum())
                    .profileIMG(member.getProfileIMG())
                    .build());

        }


        return memberDTOList;
    }
}
