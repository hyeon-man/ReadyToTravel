package kr.ac.kopo.ReadyToTravel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ReadyToTravelApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReadyToTravelApplication.class, args);
    }
//배포 설정
//@SpringBootApplication
//public class ReadyToTravelApplication extends SpringBootServletInitializer {
//    public static void main(String[] args) {
//        SpringApplication.run(ReadyToTravelApplication.class, args);
//    }
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return super.configure(builder);
//    }
}


