package kr.ac.kopo.ReadyToTravel.model;

import kr.ac.kopo.ReadyToTravel.util.FileUpload;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardVo {
    private List<MultipartFile> multipartFile;
    private List<String> filename;
    private Long boardId;
    private String boardName;
    private String boardContent;
    private Date boardDateCreate;
    private String boardWriter;

    public void setFilename(List<String> filename) {
        this.filename = filename;
    }
}
