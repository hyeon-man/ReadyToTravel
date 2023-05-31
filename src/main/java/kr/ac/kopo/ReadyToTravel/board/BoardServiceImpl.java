package kr.ac.kopo.ReadyToTravel.board;

import kr.ac.kopo.ReadyToTravel.board.reply.ReplyCustomRepository;
import kr.ac.kopo.ReadyToTravel.dto.BoardDTO;
import kr.ac.kopo.ReadyToTravel.dto.ReplyDTO;
import kr.ac.kopo.ReadyToTravel.entity.attach.BoardAttachEntity;
import kr.ac.kopo.ReadyToTravel.entity.board.BoardEntity;
import kr.ac.kopo.ReadyToTravel.util.FileUpload;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    final BoardRepository repository;
    final BoardAttachRepository boardAttachRepository;
    final BoardCustomRepository boardCustomRepository;

    final ReplyCustomRepository replyCustomRepository;

    public BoardServiceImpl(BoardRepository repository, BoardAttachRepository boardAttachRepository, BoardCustomRepository boardCustomRepository, ReplyCustomRepository replyCustomRepository) {
        this.repository = repository;
        this.boardAttachRepository = boardAttachRepository;
        this.boardCustomRepository = boardCustomRepository;
        this.replyCustomRepository = replyCustomRepository;
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
    public List<BoardDTO> boardList() {

        List<BoardDTO> boardList = boardCustomRepository.boardList();

        return boardList;
    }

    @Override
    public BoardDTO detail(Long boardNum) {
        // 게시글 상세 정보 조회
        BoardDTO detail = boardCustomRepository.getBoardDetail(boardNum);

        //게시글에 포함된 댓글의 정보 조회
        List<ReplyDTO> replies = replyCustomRepository.getReplies(boardNum);
        detail.setReplies(replies);

        // TODO: 2023-05-31 게시글에 포함된 이미지 url 까지 조회 해와야함

        return detail;
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


}

