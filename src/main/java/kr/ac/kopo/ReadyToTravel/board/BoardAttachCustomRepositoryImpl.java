package kr.ac.kopo.ReadyToTravel.board;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.ac.kopo.ReadyToTravel.dto.AttachDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

import static kr.ac.kopo.ReadyToTravel.entity.attach.QBoardAttachEntity.boardAttachEntity;


@Repository
public class BoardAttachCustomRepositoryImpl implements BoardAttachCustomRepository {

    private final JPAQueryFactory queryFactory;


    public BoardAttachCustomRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<AttachDTO> findByFileNameByBoardNum(Long boardNum) {


        return queryFactory.select(Projections.fields(AttachDTO.class,
                                boardAttachEntity.fileName.as("filename")))
                        .from(boardAttachEntity)
                        .where(boardAttachEntity.boardEntity.boardNum.eq(boardNum))
                        .fetch();
    }
}
