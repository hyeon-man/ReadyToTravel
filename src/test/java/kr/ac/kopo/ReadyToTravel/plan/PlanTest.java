package kr.ac.kopo.ReadyToTravel.plan;

import kr.ac.kopo.ReadyToTravel.dto.plan.LonLatDTO;
import kr.ac.kopo.ReadyToTravel.dto.plan.PlanDTO;
import kr.ac.kopo.ReadyToTravel.entity.plan.PlanEntity;
import kr.ac.kopo.ReadyToTravel.plan.travelType.TravelType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class PlanTest {

    @Autowired
    PlanServiceImpl planService;

    @Autowired
    PlanRepository planRepository;

    @Test
    @DisplayName("계획생성")
    public void makePlanTest() {
        // given
        PlanDTO dto = new PlanDTO();
        dto.setPlanType(TravelType.FAMILY);
        dto.setName("Test name");
        dto.setLeader("Test leader");
        dto.setContents("Test contents");

        Map<Date, List<LonLatDTO>> lonLatListByDate = new HashMap<>();
        Date date = new Date(123);
        System.out.println("date = " + date);

        List<LonLatDTO> lonLatDTOList = new ArrayList<>();
        LonLatDTO lonLat1 = new LonLatDTO();
        LonLatDTO lonLat2 = new LonLatDTO();
        LonLatDTO lonLat3 = new LonLatDTO();

        lonLat1.setLon("127");
        lonLat1.setLat("36");
        lonLat1.setCalendars(date);

        lonLat2.setLon("126");
        lonLat2.setLat("35");
        lonLat2.setCalendars(date);

        lonLat3.setLon("125");
        lonLat3.setLat("34");
        lonLat3.setCalendars(date);

        lonLatDTOList.add(lonLat1);
        lonLatDTOList.add(lonLat2);
        lonLatDTOList.add(lonLat3);

        lonLatListByDate.put(date, lonLatDTOList);

        dto.setLonLatListByDate(lonLatListByDate);

        // when
        planService.makePlan(dto);

    }

    @Test
    @DisplayName("entity to dto")
    public void entityToDto() {
        Optional<PlanEntity> planRepositoryById = planRepository.findById(1L);

        if (planRepositoryById.isPresent()) {
            System.out.println(planRepositoryById);
        }
        else System.out.println("실패");
    }
}
