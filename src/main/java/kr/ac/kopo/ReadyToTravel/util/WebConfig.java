package kr.ac.kopo.ReadyToTravel.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private String connectPath = "/img/**";
    private String resourcePath = "file:///d:/upload/";

    private String profilePath = "profile/";

    private String boardPath = "board/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(connectPath)
                .addResourceLocations(resourcePath + profilePath)
                .addResourceLocations(resourcePath + boardPath);

    }
}