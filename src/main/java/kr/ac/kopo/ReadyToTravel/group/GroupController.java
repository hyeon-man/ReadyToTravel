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

    @GetMapping("/group/generateInviteCode/{groupNum}")
    @ResponseBody
    public String generateInviteCord(@PathVariable long groupNum) {
        System.out.println("그룹 번호 = " + groupNum);

        String inviteCord = service.generateInviteCode(groupNum);

        return inviteCord;
    }

    @GetMapping("/group/joinGroup/{inviteURL}")
    public String addMember(@SessionAttribute MemberDTO memberDTO, @PathVariable String inviteURL) {

        service.groupAddMember(memberDTO.getNum(), inviteURL);
        //todo 이건 그룹 페이지로 간다
        return "redirect:/member/profile";
    }


    @GetMapping("/group/removeMember/{groupNum}")
    @ResponseBody
    public String removeMember(@PathVariable long groupNum, @SessionAttribute MemberDTO memberDTO) {

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
     * @param groupNum
     * @return
     */
    @GetMapping("/group/info/{groupNum}")
    public String groupInfo(@PathVariable long groupNum) {


        GroupDTO groupDTO = service.groupInMember(groupNum);
        System.out.println(groupDTO);

        return "/group/info";
    }
}