package kr.ac.kopo.ReadyToTravel.board;

import kr.ac.kopo.ReadyToTravel.dto.BoardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardService {
    long create(BoardDTO boardDTO);
    void update(BoardDTO boardDTO);
    void delete(Long boardNum);
    BoardDTO detail(Long boardNum);
    BoardDTO smallDetail(Long boardNum);

    Page<BoardDTO> boardList(Pageable pageable);
}
