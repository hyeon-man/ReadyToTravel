package kr.ac.kopo.ReadyToTravel.member;


import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.entity.QMemberEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static kr.ac.kopo.ReadyToTravel.entity.QMemberEntity.memberEntity;
import static kr.ac.kopo.ReadyToTravel.entity.attach.QBoardAttachEntity.boardAttachEntity;

@Repository
public class MemberCustomRepositoryImpl implements MemberCustomRepository {
    public final JPAQueryFactory queryFactory;

    public MemberCustomRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    /**
     * 중복 아이디 체크
     * @param id
     * @return
     */
    public String checkId(String id) {
        return queryFactory.select(memberEntity.memberId)
                .from(memberEntity)
                .where(memberEntity.memberId.eq(id))
                .fetchOne();
    }

    /**
     * id와 eamil을 통한 member조회 (아이디랑 이메일만)
     * @param id
     * @param email
     * @return
     */
    public MemberDTO findByIdAndEmail(String id, String email) {
        return queryFactory.select(Projections.fields(MemberDTO.class,
                        memberEntity.memberId.as("id"),
                        memberEntity.email.as("email")))
                .from(memberEntity)
                .where(memberEntity.email.eq(email), memberEntity.memberId.eq(id))
                .fetchOne();
    }


    /**
     * 비밀번호 제외 멤버의 모든 정보
     * @param memberNum
     * @return
     */
    public MemberDTO getMemberInfo(long memberNum) {
        return queryFactory.select(Projections.fields(MemberDTO.class,
                memberEntity.num.as("num"),
                memberEntity.name.as("name"),
                memberEntity.profileIMG.as("profileIMG"),
                memberEntity.signupDate.as("signupDate"),
                memberEntity.phoneNum.as("phoneNum"),
                memberEntity.email.as("email")))
                .from(memberEntity)
                .where(memberEntity.num.eq(memberNum))
                .fetchOne();
    }
}
