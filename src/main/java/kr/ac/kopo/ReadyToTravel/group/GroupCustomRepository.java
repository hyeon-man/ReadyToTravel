package kr.ac.kopo.ReadyToTravel.group;

import kr.ac.kopo.ReadyToTravel.dto.GroupDTO;
import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import kr.ac.kopo.ReadyToTravel.entity.group.GroupEntity;

import java.util.List;

public interface GroupCustomRepository {

    List<MemberDTO> groupInMember(long groupNum);
    GroupDTO groupInfo(long groupNum);
    GroupDTO myGroupNum(Long num);
}
