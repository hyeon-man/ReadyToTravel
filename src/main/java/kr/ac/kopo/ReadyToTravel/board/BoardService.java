package kr.ac.kopo.ReadyToTravel.board;

import kr.ac.kopo.ReadyToTravel.dto.BoardDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardService {
    long create(BoardDTO boardDTO);

    List<BoardDTO> findAll();


    BoardDTO findById(Long boardNum);

    void update(BoardDTO boardDTO);
    void delete(Long boardNum);


    BoardDTO findOne(Long boardNum);
}
