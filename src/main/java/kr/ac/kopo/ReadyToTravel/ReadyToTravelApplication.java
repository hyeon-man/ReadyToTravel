package kr.ac.kopo.ReadyToTravel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
@EnableCaching

@SpringBootApplication
public class ReadyToTravelApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReadyToTravelApplication.class, args);
    }

}

