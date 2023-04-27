package kr.ac.kopo.ReadyToTravel.dto;

import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import kr.ac.kopo.ReadyToTravel.entity.PlanEntity;
import kr.ac.kopo.ReadyToTravel.plan.traveltype.TravelType;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PlanDTO {

    private Long num;

    // 0 가족 여행 , 1 커플 여행 , 2 혼자 여행
    // EnumType.ORIDNAL = 값을 배열의 숫자로 반환해준다. ex) FAMILY = 0
    @Enumerated(EnumType.ORDINAL)
    private TravelType planType;

    @NotNull
    @NotBlank
    // 계획 이름
    private String name;

    @NotNull
    @NotBlank
    // 계획 주최자
    private Long leaderNum;

    private String contents;

    private List<LonLatDTO> lonLatDTOList;

    // PlanDTO 객체를 PlanEntity로 변환하는 메소드
    public PlanEntity convertToEntity(PlanDTO dto, Long memberNum) {
        PlanEntity entity = PlanEntity.builder()
                .type(dto.getPlanType())
                .name(dto.getName())
                .memberEntity(MemberEntity.builder().num(memberNum).build())
                .contents(dto.getContents())
                .build();

        return entity;
    }

}