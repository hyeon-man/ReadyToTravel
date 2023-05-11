package kr.ac.kopo.ReadyToTravel.group;


import kr.ac.kopo.ReadyToTravel.dto.GroupDTO;
import kr.ac.kopo.ReadyToTravel.dto.PlanDTO;
import kr.ac.kopo.ReadyToTravel.entity.group.GroupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface GroupService {

    void createGroup(Long planNum, Long leaderNum, String planName);

    void groupAddMember(Long memberNum, String inviteURL);
}

