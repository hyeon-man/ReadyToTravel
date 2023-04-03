package kr.ac.kopo.ReadyToTravel.board;

import kr.ac.kopo.ReadyToTravel.dto.BoardDTO;
import kr.ac.kopo.ReadyToTravel.entity.board.BoardEntity;

public interface BoardService {
    void save(BoardDTO boardDTO);
}
