package kr.ac.kopo.ReadyToTravel.plan.group;


import kr.ac.kopo.ReadyToTravel.dto.PlanDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class GroupController {
    final GroupService service;

    public GroupController(GroupService service) {
        this.service = service;
    }


    /**
     * @param planNum
     * @param leaderNum
     * @param planName
     * @return "groupPage"
     */
    @PostMapping
    public String createGroup(Long planNum, Long leaderNum, String planName) {

        service.createGroup(planNum, leaderNum, planName);


        return "";
    }

}
