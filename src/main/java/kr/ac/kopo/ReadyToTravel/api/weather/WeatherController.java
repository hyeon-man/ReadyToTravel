package kr.ac.kopo.ReadyToTravel.api.weather;


import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequiredArgsConstructor
@RestController
@RequestMapping("/weather/api")
public class WeatherController {
    AddressParse parse = new AddressParse();

    @PostMapping("/changeAddress")
    public ResponseEntity<AddressChanger> changeAddress(@RequestBody String splitResult) throws Exception {
        String substring = splitResult.substring(1, splitResult.length()-1);

        AddressChanger changer = parse.changer(substring);

        return ResponseEntity.ok().body(changer);
    }
}
