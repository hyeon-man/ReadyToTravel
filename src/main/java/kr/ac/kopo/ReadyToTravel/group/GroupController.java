package kr.ac.kopo.ReadyToTravel.group;


import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.dto.PlanDTO;
import kr.ac.kopo.ReadyToTravel.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
// TODO: 2023-05-11  1. 그룹에 속한 회원을 삭제하는 로직
public class GroupController {
    final GroupService service;
    public GroupController(GroupService service) {
        this.service = service;
    }

    @PostMapping("/group/joinGroup/{inviteURL}")
    public String addMember(@SessionAttribute(value = "memberDTO", required = false) MemberDTO memberDTO, @PathVariable String inviteURL) {
        if (memberDTO == null) {
            //todo 로그인 페이지로 갑시다
            return "loginPage";
        }
        service.groupAddMember(memberDTO.getNum(), inviteURL);
        //todo 이건 그룹 페이지로 간다
        return "";
    }

    @GetMapping("/group/removeMember/{memberNum}")
    public void removeMember(@PathVariable long memberNum){

        service.removeMember(memberNum);

    }
}