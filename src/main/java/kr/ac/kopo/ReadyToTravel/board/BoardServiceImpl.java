package kr.ac.kopo.ReadyToTravel.board;

import kr.ac.kopo.ReadyToTravel.dto.BoardDTO;
import kr.ac.kopo.ReadyToTravel.entity.attach.BoardAttachEntity;
import kr.ac.kopo.ReadyToTravel.entity.board.BoardEntity;
import kr.ac.kopo.ReadyToTravel.util.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    final BoardRepository repository;
    final BoardAttachRepository boardAttachRepository;

    public BoardServiceImpl(BoardRepository repository, BoardAttachRepository boardAttachRepository) {
        this.repository = repository;
        this.boardAttachRepository = boardAttachRepository;
    }

    @Override
    public void save(BoardDTO boardDTO) {
        // 첨부파일을 저장할 Board Attach List
        List<BoardAttachEntity> attachEntities = new ArrayList<>();

        // Dto to entity
        BoardEntity entity = BoardDTO.convertToEntity(boardDTO);

        // entity 저장하고 boardNum 가져옴
        Long boardNum = repository.save(entity).getBoardNum();

        for (int i = 0; i < boardDTO.getMultipartFile().size(); i++) {
            MultipartFile attach = boardDTO.getMultipartFile().get(i);
            String filename = FileUpload.fileUpload(attach);
//여기서 사용 했으니깐 여기쪽 한번 보셔용
            if (filename != null) {
                BoardAttachEntity attachEntity = new BoardAttachEntity();
                attachEntity.setFileName(filename);
                attachEntity.setBoardEntity(BoardEntity.builder().boardNum(boardNum).build());
                attachEntities.add(attachEntity);
            }
        }

        // attach 저장
        boardAttachRepository.saveAll(attachEntities); // 모든 BoardAttachEntity 저장
    }
}

