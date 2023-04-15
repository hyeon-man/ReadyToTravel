package kr.ac.kopo.ReadyToTravel.board;

import kr.ac.kopo.ReadyToTravel.dto.BoardDTO;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardController {
    final BoardService service;

    public BoardController(BoardService service) {
        this.service = service;
    }

    @RequestMapping("/board/create")
    public void boardCreate(BoardDTO boardDTO) {
        service.create(boardDTO);
    }

    @GetMapping("/board")
    public List<BoardDTO> boardFindAll() {
        return service.findAll();
    }

    @GetMapping("/board/{boardNum}")
    public BoardDTO boardFindById(@PathVariable BoardDTO boardDTO,Long boardNum) {
        return service.findById(boardDTO,boardNum);
    }

    @PutMapping("/board/{boardNum}")
    public BoardDTO boardUpdate(@PathVariable Long boardNum, @RequestBody BoardDTO boardDTO) {
        boardDTO.setBoardNum(boardNum);
        return service.update(boardDTO,boardNum);

    }

    @DeleteMapping("/board/{boardNum}")
    public void boardDelete(@PathVariable Long boardNum) {
        service.delete(boardNum);
    }
}
