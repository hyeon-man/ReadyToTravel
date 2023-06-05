package kr.ac.kopo.ReadyToTravel.board.reply;


import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.ac.kopo.ReadyToTravel.dto.ReplyDTO;
import kr.ac.kopo.ReadyToTravel.entity.board.QReplyEntity;
import kr.ac.kopo.ReadyToTravel.entity.board.ReplyEntity;
import org.springframework.stereotype.Repository;
import kr.ac.kopo.ReadyToTravel.entity.board.QReplyEntity.*;

import javax.persistence.EntityManager;
import java.util.List;

import static kr.ac.kopo.ReadyToTravel.entity.QMemberEntity.memberEntity;
import static kr.ac.kopo.ReadyToTravel.entity.board.QReplyEntity.replyEntity;

@Repository
public class ReplyCustomRepositoryImpl implements ReplyCustomRepository {
    public final JPAQueryFactory queryFactory;

    public ReplyCustomRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ReplyDTO> getReplies(Long boardNum) {
        return queryFactory.select(Projections.fields(ReplyDTO.class,
                        replyEntity.replyNum.as("replyNum"),
                        replyEntity.content.as("content"),
                        replyEntity.member.name.as("writerName"),
                        replyEntity.member.num.as("writer"),
                        replyEntity.writeDate.as("writeDate")))
                .from(replyEntity)
                .leftJoin(replyEntity.member, memberEntity)
                .orderBy(replyEntity.writeDate.desc())
                .where(replyEntity.boardEntity.boardNum.eq(boardNum))
                .fetch();

    }
}
