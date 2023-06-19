package kr.ac.kopo.ReadyToTravel.board;

import kr.ac.kopo.ReadyToTravel.dto.BoardDTO;
import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BoardController {
    private final BoardService service;

    public BoardController(BoardService service) {
        this.service = service;
    }

    @GetMapping("/board/list")
    public String boardList(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BoardDTO> boardPage = service.boardList(pageable);

        model.addAttribute("list", boardPage);

        return "/board/list";
    }


    @GetMapping("/board/info/{boardNum}")
    public String boardInform(@PathVariable Long boardNum, Model model) {

        BoardDTO board = service.detail(boardNum);
        model.addAttribute("board", board);

        return "/board/inform";

    }

    @GetMapping("/board/create")
    public String boardCreate() {

        return "/board/add";
    }

    @PostMapping("/board/create")
    public String boardCreate(@Validated BoardDTO boardDTO, @SessionAttribute(value = "memberDTO", required = false) MemberDTO memberDTO) {

        boardDTO.setBoardWriterNum(memberDTO.getNum());
        long boardNum = service.create(boardDTO);

        return "redirect:/board/info/" + boardNum;
    }


    @GetMapping("/board/update/{boardNum}")
    public String boardUpdate(@PathVariable Long boardNum, Model model) {

        BoardDTO board = service.smallDetail(boardNum);
        model.addAttribute("board", board);

        return "/board/update";
    }

    @PostMapping("/board/update/{boardNum}")
    public String boardUpdate(@PathVariable Long boardNum, @Validated BoardDTO board) {

        board.setBoardNum(boardNum);
        service.update(board);

        return "redirect:/board/info/" + boardNum;

    }

    @GetMapping("/board/delete/{boardNum}")
    public String boardDelete(@PathVariable Long boardNum) {

        service.delete(boardNum);

        return "redirect:/board/list";
    }
}
