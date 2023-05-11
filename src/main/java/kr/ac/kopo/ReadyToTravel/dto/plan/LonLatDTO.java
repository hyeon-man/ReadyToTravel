package kr.ac.kopo.ReadyToTravel.dto.plan;

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

    /**
     * @param dto
     * @param planNum
     * @param em      getReference : planNum이 해당하는 테이블의 행을 전부 가져와준다
     * @return dto -> entity
     */
    public static LonLatEntity convertToEntity(LonLatDTO dto, Long planNum) {
        LonLatEntity entity = LonLatEntity.builder()
                .lon(dto.getLon())
                .lat(dto.getLat())
                .calendars(dto.getCalendars())
                .planEntity(PlanEntity.builder().num(planNum).build())
                .build();

        return entity;
    }
}
