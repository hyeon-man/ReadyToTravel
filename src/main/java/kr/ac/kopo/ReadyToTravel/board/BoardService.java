package kr.ac.kopo.ReadyToTravel.board;

import kr.ac.kopo.ReadyToTravel.dto.BoardDTO;

import java.util.List;

public interface BoardService {
    void create(BoardDTO boardDTO);

    List<BoardDTO> findAll();


    BoardDTO findById(Long boardNum);

    BoardDTO update(BoardDTO boardDTO, Long boardNum);
    void delete(Long boardNum);


}
