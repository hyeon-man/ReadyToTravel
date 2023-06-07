package kr.ac.kopo.ReadyToTravel.member;

import kr.ac.kopo.ReadyToTravel.dto.MemberDTO;
import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;

public interface MemberCustomRepository {
    MemberDTO checkId(String id);

    MemberEntity findByIdAndEmail(String id, String email);

    MemberDTO findByEmail(String email);

    MemberDTO findIdAndPassword(String memberId, String encode);

    MemberDTO findByNum(Long num);
}
