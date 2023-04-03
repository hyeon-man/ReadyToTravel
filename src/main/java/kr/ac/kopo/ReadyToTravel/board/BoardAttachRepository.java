package kr.ac.kopo.ReadyToTravel.board;

import kr.ac.kopo.ReadyToTravel.entity.attach.BoardAttachEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardAttachRepository extends JpaRepository<BoardAttachEntity, Long> {
}
