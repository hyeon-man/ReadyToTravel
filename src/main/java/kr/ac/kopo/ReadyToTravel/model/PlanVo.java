package kr.ac.kopo.ReadyToTravel.model;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class PlanVo {
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
