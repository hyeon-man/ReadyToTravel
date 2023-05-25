package kr.ac.kopo.ReadyToTravel.board.reply;


import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.dto.ReplyDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/board/reply/{boardNum}")
public class ReplyController {
    final private ReplyService service;

    public ReplyController(ReplyService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public String addReply(@PathVariable long boardNum, ReplyDTO reply, HttpServletRequest request) {
        MemberDTO member = (MemberDTO) request.getAttribute("memberDTO");

        reply.setBoardNum(boardNum);
        reply.setWriter(member.getNum());

        service.addReply(reply);

        String prevPage = request.getHeader("REFERER");
        return "redirect:" + prevPage;
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

        String referer = request.getHeader("referer");

        System.out.println(referer);
        return "redirect:" + referer;
    }

}
