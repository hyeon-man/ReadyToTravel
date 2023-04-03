package kr.ac.kopo.ReadyToTravel.board;

import kr.ac.kopo.ReadyToTravel.dto.BoardDTO;
import kr.ac.kopo.ReadyToTravel.entity.attach.BoardAttachEntity;
import kr.ac.kopo.ReadyToTravel.entity.board.BoardEntity;
import kr.ac.kopo.ReadyToTravel.util.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@RestController
public class BoardController {
    final BoardService service;

    public BoardController(BoardService service) {
        this.service = service;
    }

    @RequestMapping("/board/create")
    public void boardCreate(BoardDTO boardDTO) {
        List<BoardAttachEntity> attachEntities = new ArrayList<>();

        for (int i = 0; i < boardDTO.getMultipartFile().size(); i++) {
            MultipartFile attach = boardDTO.getMultipartFile().get(i);
            String filename = FileUpload.fileUpload(attach);
            if (filename != null) {
                BoardAttachEntity attachEntity = new BoardAttachEntity();
                attachEntity.setFileName(filename);
                attachEntities.add(attachEntity);

            }
        }

        BoardEntity entity = BoardDTO.convertToEntity(boardDTO, attachEntities);
        BoardAttachEntity.attachLog(attachEntities);

        service.save(entity);

    }
}
