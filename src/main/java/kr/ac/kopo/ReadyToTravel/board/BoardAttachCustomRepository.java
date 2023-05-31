package kr.ac.kopo.ReadyToTravel.board;

import com.querydsl.jpa.impl.JPAQuery;
import kr.ac.kopo.ReadyToTravel.dto.AttachDTO;

import java.util.List;

public interface BoardAttachCustomRepository {
    List<AttachDTO> findByFileNameByBoardNum(Long boardNum);
}
