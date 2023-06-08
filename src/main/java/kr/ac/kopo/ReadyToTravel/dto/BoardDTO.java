package kr.ac.kopo.ReadyToTravel.dto;

import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import kr.ac.kopo.ReadyToTravel.entity.board.BoardEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Member;
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

    private Long boardNum;
    private String boardName;
    private String boardContent;


    private Date boardDateCreate;
    private String boardWriter;
    private String boardWriterProfile;


    private long boardWriterNum;


    private List<MultipartFile> multipartFiles;

    private List<String> filename = new ArrayList<>();

    private List<ReplyDTO> replies = new ArrayList<>();

    /**
     * @param
     * @return 사용자에게 입력받은 DTO를 토대로 Entity화 시킵니다.
     */

    public static BoardEntity convertToEntity(BoardDTO dto) {
        BoardEntity entity = BoardEntity.builder()
                .boardNum(dto.getBoardNum())
                .boardName(dto.getBoardName())
                .boardWriter(MemberEntity.builder().num(dto.getBoardWriterNum()).build())
                .boardContent(dto.getBoardContent())
                .boardDateCreate(new Date())
                .build();
        return entity;
    }
}
