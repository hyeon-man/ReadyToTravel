package kr.ac.kopo.ReadyToTravel.group;

import kr.ac.kopo.ReadyToTravel.dto.GroupDTO;

public interface GroupService {

    void createGroup(Long planNum, Long leaderNum, String planName);

    void groupAddMember(Long memberNum, String inviteURL);

    void removeMember(long memberNum);

    void updateGroup(GroupDTO group);

    GroupDTO item(long groupNum);
}

