package kr.ac.kopo.ReadyToTravel.board;


import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.ac.kopo.ReadyToTravel.dto.BoardDTO;
import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.entity.QMemberEntity;
import kr.ac.kopo.ReadyToTravel.entity.board.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;

import static kr.ac.kopo.ReadyToTravel.entity.QMemberEntity.memberEntity;
import static kr.ac.kopo.ReadyToTravel.entity.board.QBoardEntity.boardEntity;

//import static kr.ac.kopo.ReadyToTravel.entity.QMemberEntity.memberEntity;
//import static kr.ac.kopo.ReadyToTravel.entity.board.QBoardEntity.boardEntity;


@Repository
public class BoardCustomRepositoryImpl implements BoardCustomRepository {
    public final JPAQueryFactory queryFactory;

    public BoardCustomRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    /**
     * 게시글 번호, 타이틀, 작성일, 작성자 이름 조회
     * //todo 페이지네이션
     *
     * @return
     */
//    @Override
//    public List<BoardDTO> boardList() {
//        return queryFactory.select(Projections.fields(BoardDTO.class,
//                        boardEntity.boardNum.as("boardNum"),
//                        boardEntity.boardName.as("boardName"),
//                        boardEntity.boardDateCreate.as("boardDateCreate"),
//                        boardEntity.boardWriter.name.as("boardWriter")))
//                .from(boardEntity)
//                .leftJoin(boardEntity.boardWriter, memberEntity)
//                .fetch();
//    }


    /**
     * 게시글 상세 정보 조회
     *
     * @param boardNum
     * @return
     */
    @Override
    public BoardDTO getBoardDetail(Long boardNum) {
        return queryFactory.select(Projections.fields(BoardDTO.class,
                        boardEntity.boardNum.as("boardNum"),
                        boardEntity.boardName.as("boardName"),
                        boardEntity.boardContent.as("boardContent"),
                        boardEntity.boardDateCreate.as("boardDateCreate"),
                        boardEntity.boardWriter.profileIMG.as("boardWriterProfile"),
                        boardEntity.boardWriter.name.as("boardWriter")))
                .from(boardEntity)
                .leftJoin(boardEntity.boardWriter, memberEntity)
                .where(boardEntity.boardNum.eq(boardNum))
                .fetchOne();
    }

    @Override
    /**
     * 게시글 Update용 조회 게시글 제목, 내용
     */
    public BoardDTO smallDetail(Long boardNum) {
        return queryFactory.select(Projections.fields(BoardDTO.class,
                        boardEntity.boardNum.as("boardNum"),
                        boardEntity.boardName.as("boardName"),
                        boardEntity.boardContent.as("boardContent")))
                .from(boardEntity)
                .where(boardEntity.boardNum.eq(boardNum))
                .fetchOne();

    }

    @Override
    public Page<BoardDTO> boardList(Pageable pageable) {
        JPAQuery<BoardDTO> query = queryFactory
                .select(Projections.fields(BoardDTO.class,
                        boardEntity.boardNum.as("boardNum"),
                        boardEntity.boardName.as("boardName"),
                        boardEntity.boardDateCreate.as("boardDateCreate"),
                        boardEntity.boardWriter.name.as("boardWriter")))
                .from(boardEntity)
                .leftJoin(boardEntity.boardWriter, memberEntity);

        List<BoardDTO> content = query.offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = query.fetchCount();

        return new PageImpl<>(content, pageable, total);
    }
}
