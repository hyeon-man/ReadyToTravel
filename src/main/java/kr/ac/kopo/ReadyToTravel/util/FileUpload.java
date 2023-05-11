package kr.ac.kopo.ReadyToTravel.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUpload {
    static String path = "D:\\upload\\";


    /**
     * @param attach
     * @return
     * fileUpload 는 사용자에게 받은 Attach 첨부파일 (사진, 동영상 등) 을 로컬 환경에 저장하는 메소드입니다.
     *                     파일을 파라미터로 받아 저장 후 파일의 이름(String fileName)만 리턴 받습니다.
     */
    public static String fileUpload(MultipartFile attach) {
        if (attach != null && !attach.isEmpty()) {
            try {
                String filename = attach.getOriginalFilename();
                attach.transferTo(new File(path + filename));

                return filename;
            } catch (IOException e) {
                System.out.println("FileUpload.fileUpload");
                System.out.println("e.getCause() = " + e.getCause());
                e.printStackTrace();
            }
        }
        return null;
    }
}
