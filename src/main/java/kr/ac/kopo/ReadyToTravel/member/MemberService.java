package kr.ac.kopo.ReadyToTravel.member;

import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;

public interface MemberService {
    String checkId(String id);

    void singUp(MemberDTO memberDTO);

    void removeMember(Long num);

    boolean login(MemberDTO memberDTO);
}
