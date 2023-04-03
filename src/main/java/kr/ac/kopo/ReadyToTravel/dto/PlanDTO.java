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
    //todo 검증 바꿔야함 어케할지 구상

    private Long id;

    @NotNull
    private int planType;

    @NotNull
    @NotBlank
    private String planName;

    @NotNull
    @NotBlank
    private String planLeader;

}
