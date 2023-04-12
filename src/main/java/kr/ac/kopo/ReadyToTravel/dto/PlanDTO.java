package kr.ac.kopo.ReadyToTravel.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PlanDTO {

    private Long num;

    // 1 가족 여행 , 2 커플 여행 , 3, 혼자 여행
    private int planType;

    @NotNull
    @NotBlank

    // 계획의 이름
    private String planName;

    @NotNull
    @NotBlank
    // 계획 주최자
    private String planLeader;

}
