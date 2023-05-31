package kr.ac.kopo.ReadyToTravel.dto;

import kr.ac.kopo.ReadyToTravel.entity.board.ReplyEntity;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class ReplyDTO {
    private Long replyNum;
    private String content;
    private long writer;

    private String writerName;
    private Date writeDate;
    private Long boardNum;


}
