package kr.ac.kopo.ReadyToTravel.vo;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BoardVo {

    private List<MultipartFile> multipartFile = new ArrayList<>();
    private List<String> filename = new ArrayList<>();

    private Long boardId;
    private String boardName;
    private String boardContent;
    private Date boardDateCreate;
    private String boardWriter;

}
