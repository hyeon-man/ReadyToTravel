package kr.ac.kopo.ReadyToTravel.board;

import kr.ac.kopo.ReadyToTravel.board.reply.ReplyService;
import kr.ac.kopo.ReadyToTravel.dto.BoardDTO;
import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import org.springframework.stereotype.Controller;
import kr.ac.kopo.ReadyToTravel.dto.ReplyDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {
    private final BoardService service;
    private final ReplyService replyService;

    public BoardController(BoardService service, ReplyService replyService) {
        this.service = service;
        this.replyService = replyService;
    }

    @GetMapping("/board/list")
    public String boardList(Model model) {

        List<BoardDTO> boardList = service.boardList();
        model.addAttribute("list", boardList);

        return "/board/list";
    }


    @GetMapping("/board/info/{boardNum}")
    public String boardInform(@PathVariable Long boardNum, Model model) {

        BoardDTO board = service.detail(boardNum);
        model.addAttribute("board", board);

        System.out.println("board.getBoardWriterProfile() = " + board.getBoardWriterProfile());

        return "/board/inform";

    }

    @GetMapping("/board/create")
    public String boardCreate() {

        return "/board/add";
    }

    @PostMapping("/board/create")
    public String boardCreate(BoardDTO boardDTO, @SessionAttribute(value = "memberDTO", required = false) MemberDTO memberDTO) {
        boardDTO.setBoardWriterNum(memberDTO.getNum());
        long boardNum = service.create(boardDTO);

        return "redirect:/board/info/" + boardNum;
    }


    @GetMapping("/board/update/{boardNum}")
    public String boardUpdate(@PathVariable Long boardNum, Model model) {

        BoardDTO board = service.detail(boardNum);
        model.addAttribute("board", board);

        return "/board/update";
    }

    @PostMapping("/board/update/{boardNum}")
    public String boardUpdate(@PathVariable Long boardNum, BoardDTO board) {

        board.setBoardNum(boardNum);
        service.update(board);


        return "redirect:/board/info/" + boardNum;

    }

    @DeleteMapping("/board/{boardNum}")
    public void boardDelete(@PathVariable Long boardNum) {

        service.delete(boardNum);
    }
}
