package kr.ac.kopo.ReadyToTravel.root;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
/// TODO: 2023-04-02 전부 수정해야합니다.

public class RootController {
    @RequestMapping("/V3")
    public String tmapV3() {

        return "tmapV3";
    }

    @RequestMapping("/V2")
    public String tMapv2() {

        return "tmapV2";
    }

    @RequestMapping("/weather")
    public String weather() {

        return "weather";
    }

    @GetMapping("/root")
    public String index() {
        return "index";
    }
}