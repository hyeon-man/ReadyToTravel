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
    public void create(BoardDTO boardDTO) {
        List<BoardAttachEntity> attachEntities = new ArrayList<>();
        BoardEntity entity = BoardDTO.convertToEntity(boardDTO);
        // 어태치를 db에 저장 할 때에는 boardNum이 필요하니깐

        Long boardNum = repository.save(entity).getBoardNum();


        //첨부파일의 갯수만큼 반복한다
        for (int i = 0; i < boardDTO.getMultipartFile().size(); i++) {
            MultipartFile attach = boardDTO.getMultipartFile().get(i);

            String filename = FileUpload.fileUpload(attach);

            if (filename != null) {
                BoardAttachEntity attachEntity = new BoardAttachEntity();

                attachEntity.setFileName(filename);
                attachEntity.setBoardEntity(BoardEntity.builder().boardNum(boardNum).build());
                attachEntities.add(attachEntity);
            }
        }

        boardAttachRepository.saveAll(attachEntities);
    }

    @Override
    public List<BoardDTO> findAll() {

        List<BoardEntity> entityList = repository.findAll();
        //TODO entityList 이거 DTO로 convert 해서 리턴

        return null;
    }

    @Override
    public BoardDTO findById(Long boardNum) {
        BoardEntity entity = repository.findById(boardNum)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 게시번호"));

        return BoardDTO.convertToDTO(entity);
    }

    @Override
    public BoardDTO update(BoardDTO boardDTO, Long boardNum) {
        BoardEntity entity = repository.findById(boardNum)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 게시번호"));

        entity.setBoardName(boardDTO.getBoardName());
        entity.setBoardContent(boardDTO.getBoardContent());

        repository.save(entity);

        return BoardDTO.convertToDTO(entity);
    }

    @Override
    public void delete(Long boardNum) {

        repository.deleteById(boardNum);
    }
}

