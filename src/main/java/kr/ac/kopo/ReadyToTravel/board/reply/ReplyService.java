package kr.ac.kopo.ReadyToTravel.board.reply;

import kr.ac.kopo.ReadyToTravel.dto.ReplyDTO;
import kr.ac.kopo.ReadyToTravel.entity.board.ReplyEntity;

import java.util.List;

public interface ReplyService {
    void addReply(ReplyDTO reply);

    void removeReply(long boardNum, long replyNum);

    List<ReplyDTO> replyList(long boardNum);

    void updateReply(ReplyDTO replyDTO);
}
