package kr.ac.kopo.ReadyToTravel.entity.plan;

import kr.ac.kopo.ReadyToTravel.dto.plan.PlanDTO;
import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import kr.ac.kopo.ReadyToTravel.plan.travelType.TravelType;
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

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_num")
//    @Nullable
//    private PlanEntity planEntity;

    // JSON 문자열로 변환해서 저장
//    @Column(columnDefinition = "TEXT")
//    private String lonLatListByDate;

    // PlanEntity를 PlanDTO 객체로 변환하는 메소드
    public PlanDTO convertToDTO(PlanEntity entity, Long num) {

        PlanDTO dto = PlanDTO.builder()
                .num(entity.getNum())
                .planType(entity.getType())
                .name(entity.getName())
                .memberNum(num)
                .contents(entity.getContents())
                .build();

//        // JSON 문자열을 Map<Date, List<LonLatDTO>>로 변환해서 저장
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            TypeReference<Map<Date, List<LonLatDTO>>> typeRef = new TypeReference<Map<Date, List<LonLatDTO>>>() {};
//            Map<Date, List<LonLatDTO>> lonLatListByDate = objectMapper.readValue(entity.getLonLatListByDate(), typeRef);
//            dto.setLonLatListByDate(lonLatListByDate);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }

        return dto;
    }
}
