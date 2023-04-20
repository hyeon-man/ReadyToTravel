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
        PlanDTO planDTO = new PlanDTO();
        planDTO.setNum(1L);
        planDTO.setPlanType(TravelType.FAMILY);
        planDTO.setName("Test name");
        planDTO.setMemberNum(1L);
        planDTO.setContents("Test contents");

        Date date = new Date(123);
        LonLatDTO lonLatDTO1 = new LonLatDTO();
        lonLatDTO1.setPlanNum(planDTO.getNum());
        lonLatDTO1.setLon("36test");
        lonLatDTO1.setLat("127test");
        lonLatDTO1.setCalendars(date);

        LonLatDTO lonLatDTO2 = new LonLatDTO();
        lonLatDTO2.setPlanNum(planDTO.getNum());
        lonLatDTO2.setLon("36tes");
        lonLatDTO2.setLat("127tes");
        lonLatDTO2.setCalendars(date);

        LonLatDTO lonLatDTO3 = new LonLatDTO();
        lonLatDTO3.setPlanNum(planDTO.getNum());
        lonLatDTO3.setLon("36.123");
        lonLatDTO3.setLat("127.512512");
        lonLatDTO3.setCalendars(date);

        LonLatDTO lonLatDTO4 = new LonLatDTO();
        lonLatDTO4.setPlanNum(planDTO.getNum());
        lonLatDTO4.setLon("36.6161");
        lonLatDTO4.setLat("127.523234");
        lonLatDTO4.setCalendars(date);

        List<LonLatDTO> lonLatDTOList = new ArrayList<>();

        lonLatDTOList.add(lonLatDTO1);
        lonLatDTOList.add(lonLatDTO2);
        lonLatDTOList.add(lonLatDTO3);
        lonLatDTOList.add(lonLatDTO4);

        planDTO.setLonLatDTOList(lonLatDTOList);


        // when
        planService.makePlan(planDTO);

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
