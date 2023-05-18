package kr.ac.kopo.ReadyToTravel.entity.plan;

import kr.ac.kopo.ReadyToTravel.dto.plan.LonLatDTO;
import kr.ac.kopo.ReadyToTravel.plan.travelType.MarkerType;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "lonlat")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LonLatEntity {

    @Id
    @Column(name = "lonlat_num")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    @Column(name = "lonlat_lon")
    private String lon;

    @Column(name = "lonlat_lat")
    private String lat;

    @Column(name = "lonlat_calendar")
    private String calendar;

    // EnumType.STRING = db에 저장 할 때 String 형태로 저장한다 DTO에서 넘어온 배열의 숫자값으로 0 = START로 저장
    @Enumerated(EnumType.ORDINAL)
    private MarkerType markerType;

    // FetchType.LAZY = 실제 해당 데이터에 접근 할 때만 로딩하는 방식.. 성능 저하를 최소화 할 수 있다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_num")
    @Nullable
    private PlanEntity planEntity;

    public LonLatDTO convertToDTO(LonLatEntity entity, Long planNum) {
        LonLatDTO dto = LonLatDTO.builder()
                .lon(entity.getLon())
                .lat(entity.getLat())
                .calendar(entity.getCalendar())
                .planNum(PlanEntity.builder().num(planNum).build().getNum())
                .markerType(entity.getMarkerType())
                .build();

        return dto;
    }
}
