package kr.ac.kopo.ReadyToTravel.board;

import kr.ac.kopo.ReadyToTravel.dto.BoardDTO;
import kr.ac.kopo.ReadyToTravel.entity.board.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardCustomRepository {


    BoardDTO getBoardDetail(Long boardNum);

    BoardDTO smallDetail(Long boardNum);

    Page<BoardDTO> boardList(Pageable pageable);
}
