package kr.ac.kopo.ReadyToTravel.group;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.ac.kopo.ReadyToTravel.dto.GroupDTO;
import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import kr.ac.kopo.ReadyToTravel.entity.group.GroupEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.lang.reflect.Member;
import java.util.List;

import static kr.ac.kopo.ReadyToTravel.entity.QMemberEntity.*;
import static kr.ac.kopo.ReadyToTravel.entity.group.QGroupEntity.*;
import static kr.ac.kopo.ReadyToTravel.entity.group.QGroupMembership.*;
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
                        planEntity.as("plan")))
                .from(groupEntity)
                .leftJoin(planEntity)
                .on(groupEntity.plan.num.eq(planEntity.num))
                .where(groupEntity.groupNum.eq(groupNum))
                .fetchOne();
    }
}
