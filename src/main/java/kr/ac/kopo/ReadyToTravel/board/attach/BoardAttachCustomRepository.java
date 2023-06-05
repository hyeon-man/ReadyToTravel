package kr.ac.kopo.ReadyToTravel.board.attach;

import com.querydsl.jpa.impl.JPAQuery;
import kr.ac.kopo.ReadyToTravel.dto.AttachDTO;

import java.util.List;

public interface BoardAttachCustomRepository {
    List<String> findByFileNameByBoardNum(Long boardNum);
}
