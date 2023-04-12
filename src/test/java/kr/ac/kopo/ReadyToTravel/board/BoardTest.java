package kr.ac.kopo.ReadyToTravel.board;

import kr.ac.kopo.ReadyToTravel.dto.BoardDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BoardController.class)
public class BoardTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoardService boardService;

    @Test
    @DisplayName("게시글 생성 테스트")
    void boardCreate() throws Exception {

        mockMvc.perform(post("/board/create")
                        .param("title", "testTitle")
                        .param("content", "testContent"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("게시글 삭제 테스트")
    void boardRemove() throws Exception {
        Long num = 1L;
        doNothing().when(boardService).boardRemove(num);

        mockMvc.perform(post("/board/remove/{num}", num))
                .andExpect(status().isOk());
    }
}