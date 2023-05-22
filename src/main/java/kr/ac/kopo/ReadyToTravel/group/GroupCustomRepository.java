package kr.ac.kopo.ReadyToTravel.group;

import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import kr.ac.kopo.ReadyToTravel.entity.group.GroupEntity;

import java.util.List;

public interface GroupCustomRepository {
    List<GroupEntity> findAllGroup();

    List<MemberEntity> GroupInMember(long groupNum);
}
