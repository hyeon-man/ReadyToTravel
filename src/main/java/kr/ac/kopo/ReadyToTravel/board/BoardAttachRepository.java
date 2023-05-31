package kr.ac.kopo.ReadyToTravel.board;

import kr.ac.kopo.ReadyToTravel.entity.attach.BoardAttachEntity;
import kr.ac.kopo.ReadyToTravel.entity.board.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface BoardAttachRepository extends JpaRepository<BoardAttachEntity, Long> {
    List<String> findAllByBoardEntity_BoardNum(long boardNum);
}
