package kr.ac.kopo.ReadyToTravel.dto;


import kr.ac.kopo.ReadyToTravel.dto.plan.PlanDTO;
import kr.ac.kopo.ReadyToTravel.entity.group.GroupEntity;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO {

    //PK
    private Long num;

    //member num 외래키
    private Long groupLeader;

    // 계획 이름
    private long planNum;

    // 그룹에 포함된 멤버
    private List<MemberDTO> memberDTO;

    private Date modifiedDate;

    private Date createDate;

    private String name;

    private String contents;

    private PlanDTO plan;

    public void addMember(List<MemberDTO> memberList){

        this.memberDTO = memberList;

    }

    public void addPlan(PlanDTO plan) {
        this.plan = plan;
    }
}
