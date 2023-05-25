package kr.ac.kopo.ReadyToTravel.board.reply;

import kr.ac.kopo.ReadyToTravel.entity.board.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {
    void deleteByBoardEntityBoardNumAndReplyNum(Long boardEntity_boardNum, Long replyNum);

    List<ReplyEntity> findAllByBoardEntity_BoardNumOrderByWriteDate(long boardNum);
}
