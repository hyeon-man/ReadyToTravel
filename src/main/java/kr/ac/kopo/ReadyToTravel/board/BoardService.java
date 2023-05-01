package kr.ac.kopo.ReadyToTravel.board;

import kr.ac.kopo.ReadyToTravel.dto.BoardDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardService {
    void create(MultipartFile file1, MultipartFile file2, BoardDTO boardDTO);

    List<BoardDTO> findAll();


    BoardDTO findById(Long boardNum);

    BoardDTO update(BoardDTO boardDTO, Long boardNum);
    void delete(Long boardNum);


}
