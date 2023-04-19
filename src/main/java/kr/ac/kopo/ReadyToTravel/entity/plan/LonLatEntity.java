package kr.ac.kopo.ReadyToTravel.entity.plan;

import kr.ac.kopo.ReadyToTravel.dto.plan.LonLatDTO;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;

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
    private Date calendars;

    // FetchType.LAZY = 실제 해당 데이터에 접근 할 때만 로딩하는 방식.. 성능 저하를 최소화 할 수 있다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_num")
    @Nullable
    private PlanEntity planEntity;
}
