package kr.ac.kopo.ReadyToTravel.board;

import kr.ac.kopo.ReadyToTravel.entity.BoardEntity;
import kr.ac.kopo.ReadyToTravel.model.AttachVo;
import kr.ac.kopo.ReadyToTravel.model.BoardVo;
import kr.ac.kopo.ReadyToTravel.util.FileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class BoardController {

    @RequestMapping("board/create")
    public void boardCreate(BoardVo vo) throws IOException {

        for (int i = 0; i < vo.getMultipartFile().size(); i++) {
            String filename = FileUpload.fileUpload(vo.getMultipartFile().get(i));
            vo.getFilename().add(filename);
        }

        System.out.println(vo.getFilename());
    }
}
