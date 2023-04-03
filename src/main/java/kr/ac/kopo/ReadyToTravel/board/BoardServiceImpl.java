package kr.ac.kopo.ReadyToTravel.board;

import kr.ac.kopo.ReadyToTravel.entity.board.BoardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
    final BoardRepository repository;

    public BoardServiceImpl(BoardRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(BoardEntity entity) {

        repository.save(entity);
    }
}
