package kr.ac.kopo.ReadyToTravel.root;

import kr.ac.kopo.ReadyToTravel.entity.PlaceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
/// TODO: 2023-04-02 전부 수정해야합니다.

public class RootController {
    private final PlaceService placeService;

    public RootController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @RequestMapping("/weather")
    public String weather() {

        return "weather";
    }

    @RequestMapping("/guide")
    public String guide() {
        return "guide";
    }

    @GetMapping("/place")
    public String place(Model model){
        List<PlaceEntity> placeList = placeService.plaecList();
        model.addAttribute("list", placeList);

        return "place";
    }
}