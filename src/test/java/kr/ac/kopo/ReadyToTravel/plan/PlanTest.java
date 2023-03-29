package kr.ac.kopo.ReadyToTravel.plan;

import kr.ac.kopo.ReadyToTravel.entity.PlanEntity;
import kr.ac.kopo.ReadyToTravel.model.PlanVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Table;

@SpringBootTest
public class PlanTest {
    @Autowired
    PlanService service;
    @Autowired
    PlanController controller;
    PlanVo vo = new PlanVo();

    @Test
    @DisplayName("계획 생성 테스트 입니다.")
    public void makePlan() {

        service.makePlan(vo);
    }
}
