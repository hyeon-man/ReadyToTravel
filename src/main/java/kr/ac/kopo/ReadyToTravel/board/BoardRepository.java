package kr.ac.kopo.ReadyToTravel.board;

import kr.ac.kopo.ReadyToTravel.entity.board.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    BoardEntity findByBoardNum(long boardNum);
}