package kr.ac.kopo.ReadyToTravel.group;

import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.entity.QMemberEntity;
import kr.ac.kopo.ReadyToTravel.entity.group.GroupEntity;
import kr.ac.kopo.ReadyToTravel.entity.group.QGroupEntity;
import kr.ac.kopo.ReadyToTravel.member.MemberController;
import kr.ac.kopo.ReadyToTravel.member.MemberRepository;
import kr.ac.kopo.ReadyToTravel.member.MemberService;
import kr.ac.kopo.ReadyToTravel.plan.group.GroupService;
import kr.ac.kopo.ReadyToTravel.util.PassEncode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class GroupTest {
    @Autowired
    EntityManager em;
    @Autowired
    JPAQueryFactory queryFactory;

    @BeforeEach
    public void createQF() {
        queryFactory = new JPAQueryFactory(em);
    }



    @Test
    @DisplayName("QueryDsl test")
    public void test1() {
        QGroupEntity qGroupEntity = QGroupEntity.groupEntity;
        List<GroupEntity> entities = queryFactory.select(qGroupEntity)
                .from(qGroupEntity)
                .fetch();

        System.out.println(entities);
    }
}