package kr.ac.kopo.ReadyToTravel.board.attach;
import java.util.List;

public interface BoardAttachCustomRepository {
    List<String> findByFileNameByBoardNum(Long boardNum);
}
