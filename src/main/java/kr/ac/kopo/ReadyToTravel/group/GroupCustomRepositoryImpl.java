package kr.ac.kopo.ReadyToTravel.group;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import kr.ac.kopo.ReadyToTravel.entity.group.GroupEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static kr.ac.kopo.ReadyToTravel.entity.QMemberEntity.*;
import static kr.ac.kopo.ReadyToTravel.entity.group.QGroupEntity.*;
import static kr.ac.kopo.ReadyToTravel.entity.group.QGroupMembership.*;

@Repository
public class GroupCustomRepositoryImpl implements GroupCustomRepository {
    public final JPAQueryFactory queryFactory;

    public GroupCustomRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<GroupEntity> findAllGroup() {
        return queryFactory.selectFrom(groupEntity).fetch();
    }

    @Override
    @Transactional
    public List<MemberEntity> GroupInMember(long groupNum) {
        return queryFactory
                .select(memberEntity)
                .from(memberEntity)
                .leftJoin(groupMembership)
                .on(groupMembership.group.groupNum.eq(groupNum))
                .fetch();
    }
}
