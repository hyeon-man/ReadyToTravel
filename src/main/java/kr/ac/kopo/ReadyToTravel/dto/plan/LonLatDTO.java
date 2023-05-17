package kr.ac.kopo.ReadyToTravel.dto.plan;

import kr.ac.kopo.ReadyToTravel.entity.plan.LonLatEntity;
import kr.ac.kopo.ReadyToTravel.entity.plan.PlanEntity;
import kr.ac.kopo.ReadyToTravel.plan.travelType.MarkerType;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
    private String calendar;

    // 0 START , 1 VIAPOINT  , 2 END
    // EnumType.ORIDNAL = 값을 배열의 숫자로 반환해준다. ex) START = 0
    @Enumerated(EnumType.ORDINAL)
    private MarkerType markerType;
    /**
     * @param dto
     * @param planNum
     * @param em      getReference : planNum이 해당하는 테이블의 행을 전부 가져와준다
     * @return dto -> entity
     */
    public LonLatEntity convertToEntity(LonLatDTO dto, Long planNum) {
        LonLatEntity entity = LonLatEntity.builder()
                .lon(dto.getLon())
                .lat(dto.getLat())
                .calendar(dto.getCalendar())
                .planEntity(PlanEntity.builder().num(planNum).build())
                .markerType(dto.getMarkerType())
                .build();

        return entity;
    }
}
