package kr.ac.kopo.ReadyToTravel.board;

import kr.ac.kopo.ReadyToTravel.dto.BoardDTO;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
public class BoardController {
    final BoardService service;

    public BoardController(BoardService service) {
        this.service = service;
    }

    @RequestMapping("/create")
    public void boardCreate(BoardDTO boardDTO) {

        System.out.println(boardDTO);
        service.save(boardDTO);
    }

    @RequestMapping("/remove/{num}")
    public void boardRemove(@PathVariable Long num){
        service.boardRemove(num);

    }
}
