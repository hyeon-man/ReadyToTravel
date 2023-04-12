package kr.ac.kopo.ReadyToTravel.dto;


import kr.ac.kopo.ReadyToTravel.entity.group.GroupEntity;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO {

    private Long num;

    //member num 외래키
    @NotNull
    private Long groupLeader;


    @NotNull
    private String planName;


    // 그룹 포함된 멤버
    private List<MemberDTO> memberDTO;



    //TODO 메소드 내용 수정
    public GroupEntity convertToEntity(GroupDTO groupDTO){

        return GroupEntity.builder().build();
    }
}
