package kr.ac.kopo.ReadyToTravel.board;


import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.ac.kopo.ReadyToTravel.dto.BoardDTO;
import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.entity.QMemberEntity;
import kr.ac.kopo.ReadyToTravel.entity.board.BoardEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;

import static kr.ac.kopo.ReadyToTravel.entity.board.QBoardEntity.boardEntity;


@Repository
public class BoardCustomRepositoryImpl implements BoardCustomRepository {
    public final JPAQueryFactory queryFactory;

    public BoardCustomRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<BoardDTO> boardList() {
        return queryFactory.select(Projections.fields(BoardDTO.class,
                        boardEntity.boardNum.as("boardNum"),
                        boardEntity.boardName.as("boardName"),
                        boardEntity.boardDateCreate.as("boardDateCreate"),
                        boardEntity.boardWriter.memberId.as("boardWriter")))
                .from(boardEntity)
                .leftJoin(boardEntity.boardWriter, QMemberEntity.memberEntity)
                .fetch();
    }

    @Override
    public BoardDTO getBoardDetail(Long boardNum) {
        return queryFactory.select(Projections.fields(BoardDTO.class,
                        boardEntity.boardNum.as("boardNum"),
                        boardEntity.boardName.as("boardName"),
                        boardEntity.boardContent.as("boardContent"),
                        boardEntity.boardDateCreate.as("boardDateCreate"),
                        boardEntity.boardWriter.memberId.as("boardWriter")))
                .from(boardEntity)
                .leftJoin(boardEntity.boardWriter, QMemberEntity.memberEntity)
                .where(boardEntity.boardNum.eq(boardNum))
                .fetchOne();
    }
}
