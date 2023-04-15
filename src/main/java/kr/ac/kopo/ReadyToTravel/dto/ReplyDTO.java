package kr.ac.kopo.ReadyToTravel.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyDTO {
    private Long replyNum;
    private Long boardNum;
    private String content;
}
