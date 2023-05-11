package kr.ac.kopo.ReadyToTravel.member;

import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;

import javax.mail.MessagingException;

public interface MemberService {
    String checkId(String id);

    void singUp(MemberDTO memberDTO);

    void removeMember(Long num);

    boolean login(MemberDTO memberDTO);

    boolean initPass(String email) throws MessagingException;

}
