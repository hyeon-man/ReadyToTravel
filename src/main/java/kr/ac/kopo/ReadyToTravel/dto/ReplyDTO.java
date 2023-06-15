package kr.ac.kopo.ReadyToTravel.dto;

import kr.ac.kopo.ReadyToTravel.entity.board.ReplyEntity;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ReplyDTO {
    private Long replyNum;

    @NotBlank
    @Max(value = 128, message = "댓글은 128자 까지 작성 가능합니다.")
    private String content;

    private long writer;

    private String writerName;
    private Date writeDate;
    private Long boardNum;


}
