package kr.ac.kopo.ReadyToTravel.plan.group;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.ac.kopo.ReadyToTravel.entity.group.GroupEntity;
import kr.ac.kopo.ReadyToTravel.entity.group.QGroupEntity;

import java.util.List;

public abstract class GroupRepositoryImpl implements GroupRepository {
    private final JPAQueryFactory queryFactory;


    public GroupRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }



}
