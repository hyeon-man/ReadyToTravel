package kr.ac.kopo.ReadyToTravel.plan.group;

import kr.ac.kopo.ReadyToTravel.entity.group.InviteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InviteUrlRepository extends JpaRepository<InviteEntity, Long> {
    InviteEntity findByInviteURL(String inviteURL);
}
