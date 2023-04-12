package kr.ac.kopo.ReadyToTravel.root;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {

    @RequestMapping("/test")
    public String weather() {
        return "tmapV2";
    }

    @RequestMapping("/tmapTest")
    public String test() {
        return "tmapTest";
    }

    @RequestMapping("/tmapTest2")
    public String test2() {
        return "tmapTest2";
    }
}
