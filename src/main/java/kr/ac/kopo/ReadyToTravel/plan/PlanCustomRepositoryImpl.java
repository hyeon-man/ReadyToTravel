package kr.ac.kopo.ReadyToTravel.plan;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.ac.kopo.ReadyToTravel.dto.plan.PlanDTO;
import kr.ac.kopo.ReadyToTravel.entity.plan.QLonLatEntity;
import kr.ac.kopo.ReadyToTravel.entity.plan.QPlanEntity;
import kr.ac.kopo.ReadyToTravel.entity.plan.QPlanEntity.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static kr.ac.kopo.ReadyToTravel.entity.plan.QLonLatEntity.lonLatEntity;
import static kr.ac.kopo.ReadyToTravel.entity.plan.QPlanEntity.planEntity;

@Repository
public class PlanCustomRepositoryImpl implements PlanCustomRepository {
    public final JPAQueryFactory queryFactory;

    public PlanCustomRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public PlanDTO findByNum(long planNum) {
        return queryFactory.select(Projections.fields(PlanDTO.class,
                        planEntity.num.as("num"),
                        planEntity.name.as("name"),
                        planEntity.contents.as("contents")))
                .from(planEntity)
                .where(planEntity.num.eq(planNum))
                .fetchOne();
    }
}
