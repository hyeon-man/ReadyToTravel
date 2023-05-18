package kr.ac.kopo.ReadyToTravel.board;

import kr.ac.kopo.ReadyToTravel.dto.BoardDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class BoardController {
    private final BoardService service;

    public BoardController(BoardService service) {
        this.service = service;
    }

    @GetMapping("/board/create")
    public String boardCreate() {
        return "boardAdd";
        //todo 이거 작성 페이지로 보냄
    }

    @PostMapping("/board/create")
    public void boardCreate(@RequestPart(name = "file1") MultipartFile file1,
                            @RequestPart(name = "file2") MultipartFile file2,
                            @RequestPart(name = "board") BoardDTO boardDTO) {

        service.create(file1, file2, boardDTO);
    }


    @GetMapping("/board/list")
    public List<BoardDTO> boardList() {

        return service.findAll();
    }


    @GetMapping("/board/inform/{boardNum}")
    public BoardDTO boardInform(@PathVariable Long boardNum) {
        return service.findById(boardNum);
    }

    @GetMapping("/board/update/{boardNum}")
    public void boardUpdate(@PathVariable Long boardNum) {

        // TODO: 2023-04-22 페이지 리턴


    }

    @PutMapping("/board/{boardNum}")
    public BoardDTO boardUpdate(@PathVariable Long boardNum, @RequestBody BoardDTO boardDTO) {

        return service.update(boardDTO, boardNum);
    }

    @DeleteMapping("/board/{boardNum}")
    public void boardDelete(@PathVariable Long boardNum) {
        service.delete(boardNum);
    }
}
