package kr.ac.kopo.ReadyToTravel.board.reply;

import kr.ac.kopo.ReadyToTravel.dto.ReplyDTO;

import java.util.List;

public interface ReplyCustomRepository {
    List<ReplyDTO> getReplies(Long boardNum);
}
