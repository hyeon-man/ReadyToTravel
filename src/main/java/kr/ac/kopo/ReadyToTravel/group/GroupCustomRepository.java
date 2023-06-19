package kr.ac.kopo.ReadyToTravel.group;

import kr.ac.kopo.ReadyToTravel.dto.GroupDTO;
import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.dto.plan.PlanDTO;
import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import kr.ac.kopo.ReadyToTravel.entity.group.GroupEntity;

import java.util.List;

public interface GroupCustomRepository {

    List<MemberDTO> groupInMember(long groupNum);
    GroupDTO groupInfo(long groupNum);

    Long groupNum(Long planNum);

    List<Long> groupInMemberNum(Long groupNum);

    GroupDTO myGroupNum(Long memberNum);

    GroupDTO findPlanNumByGroupNum(Long groupNum);
}
