package kr.ac.kopo.ReadyToTravel.dto;

import kr.ac.kopo.ReadyToTravel.dto.attach.AttachDTO;
import kr.ac.kopo.ReadyToTravel.entity.attach.BoardAttachEntity;
import kr.ac.kopo.ReadyToTravel.entity.board.BoardEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BoardDTO {

    private List<MultipartFile> multipartFile = new ArrayList<>();
    private List<String> filename = new ArrayList<>();

    private Long boardNum;
    private String boardName;
    private String boardContent;
    private Date boardDateCreate;
    private String boardWriter;

    private List<AttachDTO> attachDTO;


    /**
     * @param dto
     * @return 사용자에게 입력받은 DTO를 토대로 Entity화 시킵니다.
     */
    public static BoardEntity convertToEntity(BoardDTO dto) {
        BoardEntity entity = BoardEntity.builder()
                .boardNum(dto.getBoardNum())
                .boardName(dto.getBoardName())
                .boardContent(dto.getBoardContent())
                .boardDateCreate(new Date())
                .boardWriter(dto.getBoardWriter())
                .build();
        return entity;
    }
    public static BoardDTO convertToDTO(BoardEntity entity) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardNum(entity.getBoardNum());
        boardDTO.setBoardName(entity.getBoardName());
        boardDTO.setBoardContent(entity.getBoardContent());
        boardDTO.setBoardDateCreate(entity.getBoardDateCreate());
        boardDTO.setBoardWriter(entity.getBoardWriter());
//        boardDTO.setAttachEntities(entity.getAttachEntities());

        return boardDTO;
    }



}
