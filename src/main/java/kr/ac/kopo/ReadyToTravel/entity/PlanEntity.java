package kr.ac.kopo.ReadyToTravel.entity;

import kr.ac.kopo.ReadyToTravel.dto.PlanDTO;
import kr.ac.kopo.ReadyToTravel.plan.traveltype.TravelType;
import lombok.*;

import javax.persistence.*;


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

    @OneToOne
    @JoinColumn(name = "member_num")
    private MemberEntity memberEntity;


    public PlanDTO convertToDTO(PlanEntity entity, Long num) {

        PlanDTO dto = PlanDTO.builder()
                .num(entity.getNum())
                .planType(entity.getType())
                .name(entity.getName())
                .leaderNum(num)
                .contents(entity.getContents())
                .build();
        return dto;
    }
}