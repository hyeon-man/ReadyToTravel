package kr.ac.kopo.ReadyToTravel.member;

import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    MemberEntity findAllByMemberId(String id);

    boolean existsByMemberIdAndPassword(String memberId, String password);

    Optional<MemberEntity> findByEmail(String email);
}
