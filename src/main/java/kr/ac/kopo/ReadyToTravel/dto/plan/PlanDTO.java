package kr.ac.kopo.ReadyToTravel.dto.plan;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.kopo.ReadyToTravel.entity.plan.PlanEntity;
import kr.ac.kopo.ReadyToTravel.plan.travelType.TravelType;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PlanDTO {

    private Long num;

    // 0 가족 여행 , 1 커플 여행 , 2 혼자 여행
    // EnumType.ORIDNAL = 값을 배열의 숫자로 반환해준다. ex) FAMILY = 0
    @Enumerated(EnumType.ORDINAL)
    private TravelType planType;

    @NotNull
    @NotBlank
    // 계획 이름
    private String name;

    @NotNull
    @NotBlank
    // 계획 주최자
    private String leader;

    private String contents;

    // 각 날짜에 해당하는 lon, lat의 정보
    private Map<Date, List<LonLatDTO>> lonLatListByDate;

    // PlanDTO 객체를 PlanEntity로 변환하는 메소드
    public PlanEntity convertToEntity(PlanDTO dto) {
        PlanEntity entity = PlanEntity.builder()
                .type(dto.getPlanType())
                .name(dto.getName())
                .leader(dto.getLeader())
                .contents(dto.getContents())
                .build();

        // Map<Date, List<LonLatDTO>>를 JSON 문자열로 변환해서 저장
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(dto.getLonLatListByDate());
            entity.setLonLatListByDate(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return entity;
    }
}
