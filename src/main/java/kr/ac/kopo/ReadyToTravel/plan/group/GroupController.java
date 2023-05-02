package kr.ac.kopo.ReadyToTravel.plan.group;


import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.dto.PlanDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GroupController {
    final GroupService service;

    public GroupController(GroupService service) {
        this.service = service;
    }


    /**
     * @param planNum
     * @param leaderNum
     * @param planName
     * @return "groupPage"
     */
    @RequestMapping
    public String createGroup(Long planNum, Long leaderNum, String planName) {

        service.createGroup(planNum, leaderNum, planName);


        return "";
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
}