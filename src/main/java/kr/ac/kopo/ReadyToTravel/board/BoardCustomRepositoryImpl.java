//package kr.ac.kopo.ReadyToTravel.board;
//
//
//import com.querydsl.core.types.Expression;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import kr.ac.kopo.ReadyToTravel.dto.BoardDTO;
//import kr.ac.kopo.ReadyToTravel.entity.board.BoardEntity;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.transaction.Transactional;
//
//@Repository
//public class BoardCustomRepositoryImpl implements BoardCustomRepository{
//    public final JPAQueryFactory queryFactory;
//
//    public BoardCustomRepositoryImpl(EntityManager em) {
//        this.queryFactory = new JPAQueryFactory(em);
//    }
//
//    @Override
//    @Transactional
//    public BoardDTO findOneGroup() {
//
//        return queryFactory.select(BoardEntity).
//
//    }
//}
