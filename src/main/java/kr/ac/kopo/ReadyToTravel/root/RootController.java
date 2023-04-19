package kr.ac.kopo.ReadyToTravel.root;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
/// TODO: 2023-04-02 전부 수정해야합니다.

public class RootController {
    @RequestMapping("/tmapTest")
    public String tmap() {

        return "tmapTest2";
    }

    @GetMapping("/root")
    public String index() {
        return "index";
    }
}