package kr.ac.kopo.ReadyToTravel.group;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.ac.kopo.ReadyToTravel.dto.GroupDTO;
import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import kr.ac.kopo.ReadyToTravel.entity.group.GroupEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static kr.ac.kopo.ReadyToTravel.entity.QMemberEntity.memberEntity;
import static kr.ac.kopo.ReadyToTravel.entity.group.QGroupEntity.groupEntity;
import static kr.ac.kopo.ReadyToTravel.entity.group.QGroupMembership.groupMembership;
import static kr.ac.kopo.ReadyToTravel.entity.plan.QPlanEntity.planEntity;

@Repository
public class GroupCustomRepositoryImpl implements GroupCustomRepository {
    public final JPAQueryFactory queryFactory;

    public GroupCustomRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<MemberDTO> groupInMember(long groupNum) {
        return queryFactory.select(Projections.fields(MemberDTO.class,
                        memberEntity.name.as("name"),
                        memberEntity.profileIMG.as("profileIMG"),
                        memberEntity.memberId.as("memberId"),
                        memberEntity.num.as("num"),
                        memberEntity.phoneNum.as("phoneNum"),
                        memberEntity.email.as("email")))
                .from(memberEntity)
                .leftJoin(groupMembership)
                .on(memberEntity.num.eq(groupMembership.member.num))
                .where(groupMembership.group.groupNum.eq(groupNum))
                .fetch();
    }


    @Override
    public GroupDTO groupInfo(long groupNum) {
        return queryFactory.select(Projections.fields(GroupDTO.class,
                        groupEntity.groupNum.as("num"),
                        groupEntity.name.as("name"),
                        groupEntity.createDate.as("createDate"),
                        groupEntity.modifiedDate.as("modifiedDate"),
                        planEntity.num.as("planNum"),
                        planEntity.leaderNum.num.as("groupLeader"),
                        planEntity.contents.as("contents")))
                .from(groupEntity)
                .leftJoin(planEntity)
                .on(groupEntity.plan.num.eq(planEntity.num))
                .where(groupEntity.groupNum.eq(groupNum))
                .fetchOne();
    }

    @Override
    public Long groupNum(Long planNum) {
        GroupDTO groupDTO =  queryFactory.select(Projections.fields(GroupDTO.class,
                groupEntity.groupNum.as("num")))
                .from(groupEntity)
                .where(groupEntity.plan.num.eq(planNum))
                .fetchOne();

        Long groupNum = groupDTO.getNum();

        return groupNum;
    }

    @Override
    public List<Long> groupInMemberNum(Long groupNum) {
        List<MemberDTO> memberDTO = queryFactory.select(Projections.fields(MemberDTO.class,
                        groupMembership.member.num.as("num")))
                .from(groupMembership)
                .where(groupMembership.group.groupNum.eq(groupNum))
                .fetch();

        List<Long> memberList = new ArrayList<>();
        for (MemberDTO dto : memberDTO) {
            memberList.add(dto.getNum());
        }
        return memberList;
    }


    @Override
    public GroupDTO myGroupNum(Long memberNum) {
        return queryFactory.select(Projections.fields(GroupDTO.class,
                        groupEntity.groupNum.as("num"),
                        groupEntity.name.as("name")))
                .from(groupEntity)
                .leftJoin(groupMembership)
                .on(groupEntity.groupNum.eq(groupMembership.group.groupNum))
                .where(groupMembership.member.num.eq(memberNum))
                .fetchOne();
    }

    @Override
    public GroupDTO findPlanNumByGroupNum(Long groupNum) {
        return queryFactory.select(Projections.fields(GroupDTO.class,
                groupEntity.plan.num.as("planNum")))
                .from(groupEntity)
                .where(groupEntity.groupNum.eq(groupNum))
                .fetchOne();
    }


}
