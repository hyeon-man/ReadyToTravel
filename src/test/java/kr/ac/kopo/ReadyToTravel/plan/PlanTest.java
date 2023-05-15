package kr.ac.kopo.ReadyToTravel.plan;

import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.dto.plan.LonLatDTO;
import kr.ac.kopo.ReadyToTravel.dto.plan.PlanDTO;
import kr.ac.kopo.ReadyToTravel.entity.plan.PlanEntity;
import kr.ac.kopo.ReadyToTravel.group.GroupServiceImpl;
import kr.ac.kopo.ReadyToTravel.plan.travelType.TravelType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.*;

@SpringBootTest
@WebAppConfiguration
public class PlanTest {
    @Autowired
    PlanServiceImpl planService;

    @Autowired
    PlanRepository planRepository;

    @Autowired
    GroupServiceImpl groupService;

    @Test
    @DisplayName("계획생성")
    public void makePlanTest() {

        // given
//        MemberDTO memberDTO = new MemberDTO();
//        memberDTO.setNum(1L);
//        memberDTO.setMemberId("member");
//        memberDTO.setPassword("1234");
//        memberDTO.setEmail("memberTest@test.com");
//        memberDTO.setPhoneNum("010-1234-5678");
//        memberDTO.setProfileIMG("testImg");
//        memberDTO.setSignupDate(new Date());

        PlanDTO planDTO = new PlanDTO();
        planDTO.setNum(null);
        planDTO.setPlanType(TravelType.FAMILY);
        planDTO.setName("Test Create Plan");
        planDTO.setContents("Test Contents");
        planDTO.setLeaderNum(1L);

        List<LonLatDTO> lonLatDTOList = new ArrayList<LonLatDTO>();

        for (int i = 0; i < 3; i++) {
            LonLatDTO lonLatDTO = new LonLatDTO();
            lonLatDTO.setLat("test lat" + i);
            lonLatDTO.setLon("test lon" + i);
            lonLatDTO.setCalendars("2023-05-03");

            lonLatDTOList.add(lonLatDTO);
        }
        planDTO.setLonLatDTOList(lonLatDTOList);

        planService.createPlan(planDTO);
    }

    @Test
    @DisplayName("Create Group")
    public void createGroup() {
        groupService.createGroup(1L, 1L, "1234");
    }

    @Test
    @DisplayName("entity to dto")
    public void entityToDto() {
        Optional<PlanEntity> planRepositoryById = planRepository.findById(1L);

        if (planRepositoryById.isPresent()) {
            System.out.println(planRepositoryById);
        } else System.out.println("실패");
    }
}
