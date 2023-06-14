package kr.ac.kopo.ReadyToTravel.dto;

import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import kr.ac.kopo.ReadyToTravel.entity.board.BoardEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotBlank(message = "제목은 공백 일 수 없습니다.")
    @Size(max = 32, message = "제목은 32자 까지 작성 할 수 있습니다.")
    private String boardName;

    @NotBlank(message = "내용은 공백 일 수 없습니다.")
    @Size(max = 512, message = "내용은 512자 까지 작성 할 수 있습니다.")
    private String boardContent;

    private Date boardDateCreate;
    private String boardWriter;

    private String boardWriterProfile;
    private long boardWriterNum;

    private List<MultipartFile> multipartFiles;

    private List<String> filename;
    private List<ReplyDTO> replies;

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
