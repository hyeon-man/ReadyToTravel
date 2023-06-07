package kr.ac.kopo.ReadyToTravel.board.reply;


import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.dto.ReplyDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/board/reply/{boardNum}")
public class ReplyController {
    final private ReplyService service;

    public ReplyController(ReplyService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public String addReply(@PathVariable long boardNum,
                           ReplyDTO reply,
                           @SessionAttribute(value = "memberDTO", required = false) MemberDTO memberDTO,
                           HttpServletRequest request) {
        reply.setBoardNum(boardNum);
        reply.setWriter(memberDTO.getNum());

        service.addReply(reply);

        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

    @GetMapping("/remove/{replyNum}")
    public String removeReply(@PathVariable long replyNum,
                              @PathVariable long boardNum,
                              HttpServletRequest request) {
        service.removeReply(boardNum, replyNum);


        String referer = request.getHeader("referer");

        System.out.println(referer);
        return "redirect:" + referer;
    }

    @PostMapping("/update/{replyNum}")
    public String update(@PathVariable long boardNum, ReplyDTO reply,HttpServletRequest request) {
        reply.setBoardNum(boardNum);
        service.updateReply(reply);

        System.out.println("reply.getContent() = " + reply.getContent());
        String referer = request.getHeader("referer");

        System.out.println(referer);
        return "redirect:" + referer;
    }

}
