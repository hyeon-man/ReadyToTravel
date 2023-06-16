package kr.ac.kopo.ReadyToTravel.entity.plan;

import kr.ac.kopo.ReadyToTravel.dto.plan.PlanDTO;
import kr.ac.kopo.ReadyToTravel.dto.plan.LonLatDTO;
import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import kr.ac.kopo.ReadyToTravel.plan.travelType.TravelType;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "plan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_num")
    private Long num;

    // EnumType.STRING = db에 저장 할 때 String 형태로 저장한다 DTO에서 넘어온 배열의 숫자값으로 0 = FAMILY로 저장
    @Enumerated(EnumType.STRING)
    private TravelType type;

    @Column(nullable = false)
    private String name;

    @Column
    private String contents;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    @OneToOne
    @JoinColumn(name = "leader_num")
    private MemberEntity leaderNum;



    public PlanDTO convertToDTO(PlanEntity entity, Long num, List<LonLatEntity> lonlat) {
        LonLatEntity lonLatEntity = new LonLatEntity();
        List<LonLatDTO> lonLatDTOList = new ArrayList<>();

        for (int i = 0; i < lonlat.size(); i++) {
            LonLatDTO lonLatConvertToDTO = lonLatEntity.convertToDTO(lonlat.get(i), num);
            lonLatDTOList.add(lonLatConvertToDTO);
        }

        PlanDTO dto = PlanDTO.builder()
                .num(entity.getNum())
                .type(entity.getType())
                .name(entity.getName())
                .leaderNum(num)
                .contents(entity.getContents())
                .createDate(entity.createDate)
                .lonLatList(lonLatDTOList)
                .build();
        return dto;
    }
}
