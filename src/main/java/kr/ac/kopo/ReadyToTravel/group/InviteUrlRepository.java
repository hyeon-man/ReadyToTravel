package kr.ac.kopo.ReadyToTravel.group;

import kr.ac.kopo.ReadyToTravel.entity.group.InviteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InviteUrlRepository extends JpaRepository<InviteEntity, Long> {
    InviteEntity findByInviteURL(String inviteURL);

    InviteEntity findByGroupEntity_GroupNum(long groupNum);
}
