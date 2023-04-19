package kr.ac.kopo.ReadyToTravel.dto.plan;

import kr.ac.kopo.ReadyToTravel.entity.board.BoardEntity;
import kr.ac.kopo.ReadyToTravel.entity.plan.LonLatEntity;
import kr.ac.kopo.ReadyToTravel.entity.plan.PlanEntity;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LonLatDTO {

    // 36. ~~~
    @NotNull
    private String lon;

    // 127. ~~~
    @NotNull
    private String lat;


    // plan 외래키
    private Long planNum;

    // 등록된 날짜
    private Date calendars;

    // dto를 entity로 변환해주는 method
    public LonLatEntity convertToEntity(LonLatDTO dto, Long planNum) {
        PlanEntity pe = PlanEntity.builder()
                .num(planNum)
                .build();

        LonLatEntity entity = LonLatEntity.builder()
                .lon(dto.getLon())
                .lat(dto.getLat())
                .calendars(dto.getCalendars())
                .planEntity(pe)
                .build();

        return entity;
    }
}
