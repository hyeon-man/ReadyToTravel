package kr.ac.kopo.ReadyToTravel.plan;

import kr.ac.kopo.ReadyToTravel.dto.PlanDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PlanTest {
    @Autowired
    PlanService service;
    @Autowired
    PlanController controller;
    PlanDTO vo = new PlanDTO();

    @Test
    @DisplayName("계획 생성 테스트 입니다.")
    public void makePlan() {

        service.makePlan(vo);
    }
}
