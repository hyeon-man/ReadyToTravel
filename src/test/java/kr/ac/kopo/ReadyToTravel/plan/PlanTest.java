//package kr.ac.kopo.ReadyToTravel.plan;
//
//import kr.ac.kopo.ReadyToTravel.dto.plan.PlanDTO;
//import kr.ac.kopo.ReadyToTravel.dto.plan.LonLatDTO;
//import kr.ac.kopo.ReadyToTravel.entity.plan.LonLatEntity;
//import kr.ac.kopo.ReadyToTravel.entity.plan.PlanEntity;
//import kr.ac.kopo.ReadyToTravel.group.GroupServiceImpl;
//import kr.ac.kopo.ReadyToTravel.plan.travelType.MarkerType;
//import kr.ac.kopo.ReadyToTravel.plan.travelType.TravelType;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//@SpringBootTest
//@WebAppConfiguration
//public class PlanTest {
//    @Autowired
//    PlanServiceImpl planService;
//
//    @Autowired
//    PlanRepository planRepository;
//
//    @Autowired
//    GroupServiceImpl groupService;
//
//    @Test
//    @DisplayName("계획생성")
//    public void makePlanTest() {
//
//        PlanDTO planDTO = new PlanDTO();
//        planDTO.setNum(null);
//        planDTO.setPlanType(TravelType.FAMILY);
//        planDTO.setName("Test Create Plan");
//        planDTO.setContents("Test Contents");
//        planDTO.setLeaderNum(1L);
//        planDTO.setCreateDate(new Date());
//
//        List<LonLatDTO> lonLatDTOList = new ArrayList<>();
//
//        LonLatDTO lonLatDTO1 = new LonLatDTO();
//        lonLatDTO1.setPlanNum(planDTO.getNum());
//        lonLatDTO1.setLon("1");
//        lonLatDTO1.setLat("1");
//        lonLatDTO1.setCalendar("2023-05-17");
//        lonLatDTO1.setMarkerType(MarkerType.START);
//
//        LonLatDTO lonLatDTO2 = new LonLatDTO();
//        lonLatDTO2.setPlanNum(planDTO.getNum());
//        lonLatDTO2.setLon("2");
//        lonLatDTO2.setLat("2");
//        lonLatDTO2.setCalendar("2023-05-18");
//        lonLatDTO2.setMarkerType(MarkerType.VIAPOINT);
//
//        LonLatDTO lonLatDTO3 = new LonLatDTO();
//        lonLatDTO3.setPlanNum(planDTO.getNum());
//        lonLatDTO3.setLon("3");
//        lonLatDTO3.setLat("3");
//        lonLatDTO3.setCalendar("2023-05-19");
//        lonLatDTO3.setMarkerType(MarkerType.VIAPOINT);
//
//        LonLatDTO lonLatDTO4 = new LonLatDTO();
//        lonLatDTO4.setPlanNum(planDTO.getNum());
//        lonLatDTO4.setLon("4");
//        lonLatDTO4.setLat("4");
//        lonLatDTO4.setCalendar("2023-05-20");
//        lonLatDTO4.setMarkerType(MarkerType.END);
//
//        lonLatDTOList.add(lonLatDTO1);
//        lonLatDTOList.add(lonLatDTO2);
//        lonLatDTOList.add(lonLatDTO3);
//        lonLatDTOList.add(lonLatDTO4);
//
//        planService.createPlan(planDTO);
//    }
//
//    @Test
//    @DisplayName("Create Group")
//    public void createGroup() {
//        groupService.createGroup(1L, 1L, "1234");
//    }
//
//    @Test
//    @DisplayName("entity to dto")
//    public void entityToDto() {
//        Optional<PlanEntity> planRepositoryById = planRepository.findById(1L);
//
//        if (planRepositoryById.isPresent()) {
//            System.out.println(planRepositoryById);
//        } else System.out.println("실패");
//    }
//
//    @Test
//    @DisplayName("plan entity to dto LonLatList 불러오기")
//    public void planEntityToDTO() {
//        PlanEntity planEntity = new PlanEntity();
//        planEntity.setNum(null);
//        planEntity.setType(TravelType.FAMILY);
//        planEntity.setName("Entity Test");
//        planEntity.setContents("Entity Test");
//        planEntity.setCreateDate(new Date());
//
//        Long planNum = planEntity.getNum();
//
//        List<LonLatEntity> lonLatEntityList = new ArrayList<>();
//
//        LonLatDTO lonLatDTO1 = new LonLatDTO();
//        lonLatDTO1.setPlanNum(planEntity.getNum());
//        lonLatDTO1.setLon("1");
//        lonLatDTO1.setLat("1");
//        lonLatDTO1.setCalendar("2023-05-17");
//        lonLatDTO1.setMarkerType(MarkerType.START);
//        LonLatEntity lonLatEntity1 = lonLatDTO1.convertToEntity(lonLatDTO1, planEntity.getNum());
//
//        LonLatDTO lonLatDTO2 = new LonLatDTO();
//        lonLatDTO2.setPlanNum(planEntity.getNum());
//        lonLatDTO2.setLon("2");
//        lonLatDTO2.setLat("2");
//        lonLatDTO2.setCalendar("2023-05-18");
//        lonLatDTO2.setMarkerType(MarkerType.VIAPOINT);
//        LonLatEntity lonLatEntity2 = lonLatDTO2.convertToEntity(lonLatDTO2, planEntity.getNum());
//
//        LonLatDTO lonLatDTO3 = new LonLatDTO();
//        lonLatDTO3.setPlanNum(planEntity.getNum());
//        lonLatDTO3.setLon("3");
//        lonLatDTO3.setLat("3");
//        lonLatDTO3.setCalendar("2023-05-19");
//        lonLatDTO3.setMarkerType(MarkerType.VIAPOINT);
//        LonLatEntity lonLatEntity3 = lonLatDTO3.convertToEntity(lonLatDTO3, planEntity.getNum());
//
//        LonLatDTO lonLatDTO4 = new LonLatDTO();
//        lonLatDTO4.setPlanNum(planEntity.getNum());
//        lonLatDTO4.setLon("4");
//        lonLatDTO4.setLat("4");
//        lonLatDTO4.setCalendar("2023-05-20");
//        lonLatDTO4.setMarkerType(MarkerType.END);
//        LonLatEntity lonLatEntity4 = lonLatDTO4.convertToEntity(lonLatDTO4, planEntity.getNum());
//
//        lonLatEntityList.add(lonLatEntity1);
//        lonLatEntityList.add(lonLatEntity2);
//        lonLatEntityList.add(lonLatEntity3);
//        lonLatEntityList.add(lonLatEntity4);
//
//        PlanDTO planDTO = planEntity.convertToDTO(planEntity, planNum, lonLatEntityList);
//        System.out.println("planDTO = " + planDTO);
//    }
//}
