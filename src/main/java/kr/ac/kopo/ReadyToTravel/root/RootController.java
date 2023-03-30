package kr.ac.kopo.ReadyToTravel.root;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class RootController {
    @RequestMapping("/tmapV3")
    public String tmapV3() {

        return "tmapV3";
    }

    @RequestMapping("/tmapV2")
    public String tMapv2() {

        return "tmapV2";
    }

    @RequestMapping("/weather")
    public String weather() {

        return "weather";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
