package kr.ac.kopo.ReadyToTravel.board;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.ac.kopo.ReadyToTravel.entity.attach.QBoardAttachEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class BoardAttachCustomRepositoryImpl implements BoardAttachCustomRepository {

    private final JPAQueryFactory queryFactory;


    public BoardAttachCustomRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<String> findByFileNameByBoardNum(Long boardNum) {
        return (List<String>) queryFactory.select(QBoardAttachEntity.boardAttachEntity.fileName)
                .from(QBoardAttachEntity.boardAttachEntity)
                .where(QBoardAttachEntity.boardAttachEntity.boardEntity.boardNum.eq(boardNum));
    }
}
