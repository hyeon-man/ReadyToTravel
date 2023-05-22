package kr.ac.kopo.ReadyToTravel.root;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
/// TODO: 2023-04-02 전부 수정해야합니다.

public class RootController {

    @RequestMapping("/weather")
    public String weather() {

        return "weather";
    }

    @RequestMapping("/guide")
    public String guide() {
        return "guide";
    }

    @RequestMapping("/boardList")
    public String boardList() {
        return "list";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/testList")
    public String testList(){
        return "testList";
    }

    @GetMapping("/place")
    public String place(){
        return "place";
    }
}