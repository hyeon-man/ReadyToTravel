package kr.ac.kopo.ReadyToTravel.api.weather;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class AddressChanger implements Serializable {

    private String city;

    private String cityCode;
    public static AddressChanger get(String city, String cityCode) {
        return AddressChanger.builder()
                .city(city)
                .cityCode(cityCode)
                .build();
    }
}
