package kr.ac.kopo.ReadyToTravel.vo;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PlanVo {
    //todo 이거 검증식 바꿔야합니다


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
