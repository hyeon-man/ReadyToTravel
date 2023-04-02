package kr.ac.kopo.ReadyToTravel.board;

import kr.ac.kopo.ReadyToTravel.vo.BoardVo;
import kr.ac.kopo.ReadyToTravel.util.FileUpload;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//todo 이거 Rest 하게 할건지 동기로 할건지 선택 해야함
public class BoardController {
    @RequestMapping("/board/create")
    public void boardCreate(BoardVo vo){


        //todo Controller => Service 로 로직 이동.
        for (int i = 0; i < vo.getMultipartFile().size(); i++) {
            String filename = FileUpload.fileUpload(vo.getMultipartFile().get(i));
            if (filename != null) {
                vo.getFilename().add(filename);
            }
        }
        System.out.println(vo.getFilename());
    }
}
