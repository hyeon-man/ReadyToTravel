package kr.ac.kopo.ReadyToTravel.plan.group;


import kr.ac.kopo.ReadyToTravel.dto.GroupDTO;
import kr.ac.kopo.ReadyToTravel.entity.group.GroupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface GroupService {
    void createGroup(GroupDTO groupDTO);

    void removeGroup(Long groupNum);


    // TODO: 2023-04-12 이거 구현해야됨 
    void groupAddMember(Long GroupNum, Long memberNum);

}

