package kr.ac.kopo.ReadyToTravel.group;


import kr.ac.kopo.ReadyToTravel.dto.GroupDTO;
import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
// TODO: 2023-05-11
//  1. 그룹에 속한 회원을 삭제하는 로직 ! 완료
//  2. 그룹에 대한 정보를 변경하는 로직
//  3. 그룹에 대한

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

    @PostMapping("/group/updateGroup/{groupNum}")
    public void updateGroup(@PathVariable long groupNum, GroupDTO group){
        group.setNum(groupNum);
        service.updateGroup(group);
    }

    @GetMapping("/group/info/{groupNum}")
    public String groupInfo(@PathVariable long groupNum,  Model model ){

        GroupDTO group = service.item(groupNum);
        model.addAttribute("group", group);


        return "/group/info";
    }
}