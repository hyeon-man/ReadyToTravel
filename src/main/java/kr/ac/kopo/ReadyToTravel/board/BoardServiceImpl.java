package kr.ac.kopo.ReadyToTravel.board;

import kr.ac.kopo.ReadyToTravel.dto.BoardDTO;
import kr.ac.kopo.ReadyToTravel.entity.attach.BoardAttachEntity;
import kr.ac.kopo.ReadyToTravel.entity.board.BoardEntity;
import kr.ac.kopo.ReadyToTravel.util.FileUpload;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {
    final BoardRepository repository;
    final BoardAttachRepository boardAttachRepository;

    public BoardServiceImpl(BoardRepository repository, BoardAttachRepository boardAttachRepository) {
        this.repository = repository;
        this.boardAttachRepository = boardAttachRepository;
    }

    @Override
    public long create(BoardDTO boardDTO) {
        List<BoardAttachEntity> attachEntities = new ArrayList<>();
        BoardEntity entity = BoardDTO.convertToEntity(boardDTO);

        Long boardNum = repository.save(entity).getBoardNum();


        //첨부파일의 갯수만큼 반복한다
        for (int i = 0; i < boardDTO.getMultipartFiles().size(); i++) {
            MultipartFile attach = boardDTO.getMultipartFiles().get(i);
            String filename = FileUpload.fileUpload(attach);
            if (filename != null) {
                BoardAttachEntity attachEntity = new BoardAttachEntity();
                attachEntity.setFileName(filename);
                attachEntity.setBoardEntity(BoardEntity.builder().boardNum(boardNum).build());
                attachEntities.add(attachEntity);
            }
        }
        boardAttachRepository.saveAll(attachEntities);

        return boardNum;
    }

    @Override
    public List<BoardDTO> findAll() {

        List<BoardEntity> entityList = repository.findAll();
        List<BoardDTO> boardList = new ArrayList<>();

        for (BoardEntity boardEntity : entityList) {
            BoardDTO dto = BoardDTO.convertToDTO(boardEntity);
            boardList.add(dto);
        }

        return boardList;
    }

    @Override
    public BoardDTO findById(Long boardNum) {
        BoardEntity entity = repository.findById(boardNum)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 게시번호"));

        return BoardDTO.convertToDTO(entity);
    }

    @Override
    public void update(BoardDTO boardDTO) {
        BoardEntity entity = repository.findById(boardDTO.getBoardNum())
                .orElseThrow(() -> new IllegalArgumentException("잘못된 게시글 번호"));

        entity.setBoardName(boardDTO.getBoardName());
        entity.setBoardContent(boardDTO.getBoardContent());

        repository.save(entity);
    }

    @Override
    public void delete(Long boardNum) {

        repository.deleteById(boardNum);
    }

    @Override
    public BoardDTO findOne(Long boardNum) {
        BoardEntity boardEntity = repository.findById(boardNum).orElseThrow(() -> new NullPointerException("해당 게시글이 존재하지 않습니다"));

        return BoardDTO.builder()
                .boardContent(boardEntity.getBoardContent())
                .boardName(boardEntity.getBoardName())
                .boardWriter(boardEntity.getBoardWriter())
                .boardDateCreate(boardEntity.getBoardDateCreate())
                .boardNum(boardEntity.getBoardNum())
                .build();

    }
}

