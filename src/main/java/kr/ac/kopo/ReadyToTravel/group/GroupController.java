package kr.ac.kopo.ReadyToTravel.group;


import kr.ac.kopo.ReadyToTravel.dto.GroupDTO;
import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: 2023-05-11
//  1. 그룹에 속한 회원을 삭제하는 로직 ! 완료
//  2. 그룹에 대한 정보를 변경하는 로직
//  3. 그룹에 대한
@Controller
public class GroupController {
    final GroupService service;

    public GroupController(GroupService service) {
        this.service = service;
    }

    @GetMapping("/group/generateInviteCode")
    @ResponseBody
    public String generateInviteCord(GroupDTO groupDTO) {

        System.out.println("그룹 번호 = " + groupDTO.getNum());

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
        //todo 이건 그룹 페이지로 간다
        return "완료";
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

    @GetMapping("/group/info/{groupNum}")
    public String groupInfo(@PathVariable long groupNum, Model model) {

        GroupDTO group = service.item(groupNum);
        model.addAttribute("group", group);

        List<MemberDTO> memberList = service.groupInMember(groupNum);
        model.addAttribute("memberList", memberList);

        return "/group/info";
    }
}