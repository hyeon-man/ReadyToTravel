package kr.ac.kopo.ReadyToTravel.group;


import kr.ac.kopo.ReadyToTravel.dto.GroupDTO;
import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GroupController {
    final GroupService service;

    public GroupController(GroupService service) {
        this.service = service;
    }

    @GetMapping("/group/generateInviteCode")
    @ResponseBody
    public String generateInviteCode(GroupDTO groupDTO) {

        String inviteCord = service.generateInviteCode(groupDTO.getNum());

        return inviteCord;
    }

    @GetMapping("/group/joinGroup/{inviteURL}")
    @ResponseBody
    public String addMember(@SessionAttribute(value = "memberDTO", required = false) MemberDTO memberDTO, @PathVariable String inviteURL) {
        if (memberDTO == null) {

            return "로그인이 필요한 기능입니다";
        }

        service.groupAddMember(memberDTO.getNum(), inviteURL);

        return "redirect:/member/profile";
    }


    @GetMapping("/group/removeMember/{groupNum}")
    @ResponseBody
    public String removeMember(@PathVariable long groupNum, @SessionAttribute(value = "memberDTO", required = false) MemberDTO memberDTO) {

        service.removeMember(groupNum, memberDTO.getNum());


        return "삭제 완료";
    }

    @PostMapping("/group/updateGroup/{groupNum}")
    public void updateGroup(@PathVariable long groupNum, GroupDTO group) {
        group.setNum(groupNum);
        service.updateGroup(group);
    }


    /**
     * 그룹 조회
     *
     * @param groupNum
     * @return
     */
    @GetMapping("/group/info/{groupNum}")
    public String groupInfo(@PathVariable long groupNum) {

        GroupDTO groupDTO = service.groupInMember(groupNum);

        return "/group/info";
    }
}