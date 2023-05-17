package kr.ac.kopo.ReadyToTravel.group;


public interface GroupService {

    void createGroup(Long planNum, Long leaderNum, String planName);

    void groupAddMember(Long memberNum, String inviteURL);

    void removeMember(long memberNum);
}

