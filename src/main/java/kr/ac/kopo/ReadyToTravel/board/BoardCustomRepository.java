package kr.ac.kopo.ReadyToTravel.board;

import kr.ac.kopo.ReadyToTravel.dto.BoardDTO;
import kr.ac.kopo.ReadyToTravel.entity.board.BoardEntity;

import java.util.List;

public interface BoardCustomRepository {

    List<BoardDTO> boardList();

    BoardDTO getBoardDetail(Long boardNum);
}
