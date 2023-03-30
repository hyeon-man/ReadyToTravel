package kr.ac.kopo.ReadyToTravel.util;

import kr.ac.kopo.ReadyToTravel.model.AttachVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class FileUpload {
    static String path = "D:\\upload\\";


    public static String fileUpload(MultipartFile attach) throws IOException {
        if (attach != null && !attach.isEmpty()) {
            String filename = attach.getOriginalFilename();
            attach.transferTo(new File(path + filename));

            return filename;
        } else {
            return "비정상적 파일";
        }
    }
}
