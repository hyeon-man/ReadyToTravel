package kr.ac.kopo.ReadyToTravel.api.weather;

import java.util.HashMap;
import java.util.Map;

public class AddressParse {

    private final Map<String, String> locationMap = new HashMap<String, String>() {{
        put("서울특별시", "11B00000");
        put("강원도", "11D10000");
        put("인천광역시", "11B00000");
        put("경기도", "11B00000");
        put("대전광역시", "11C20000");
        put("세종특별자치시", "11C20000");
        put("충청남도", "11C20000");
        put("충청북도", "11C10000");
        put("광주광역시", "11F20000");
        put("전라남도", "11F20000");
        put("전라북도", "11F10000");
        put("대구광역시", "11H10000");
        put("경상북도", "11H10000");
        put("부산광역시", "11H20000");
        put("울산광역시", "11H20000");
        put("경상남도", "11H20000");
        put("제주도특별자치도", "11G00000");
    }};

    public AddressChanger changer(String address) {
        if (locationMap.containsKey(address)) {
            return AddressChanger.get(address, locationMap.get(address));
        }

        return null;
    }
}