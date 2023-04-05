package kr.ac.kopo.ReadyToTravel.board;

import kr.ac.kopo.ReadyToTravel.dto.BoardDTO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {
    final BoardService service;

    public BoardController(BoardService service) {
        this.service = service;
    }

    @RequestMapping("/board/create")
    public void boardCreate(BoardDTO boardDTO) {
        service.save(boardDTO);
    }
}
