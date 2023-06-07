//package kr.ac.kopo.ReadyToTravel.attach;
//
//
//import kr.ac.kopo.ReadyToTravel.board.BoardController;
//import kr.ac.kopo.ReadyToTravel.util.FileUpload;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.mock.web.MockMultipartFile;
//
//import java.io.IOException;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@SpringBootTest
//public class AttachTest {
//
//    @Autowired
//    BoardController controller;
//
//    @Test
//    @DisplayName("파일 저장 테스트")
//    void testFileUpload() throws IOException {
//        MockMultipartFile file = new MockMultipartFile(
//                "file",
//                "test.txt",
//                "text/plain",
//                "hello world".getBytes()
//        );
//
//        // 파일 업로드
//        String result = FileUpload.fileUpload(file);
//        System.out.println(result);
//        // 파일이 정상적으로 업로드 되었는지 확인
//        assertEquals("test.txt", result);
//
//    }
//
//}
