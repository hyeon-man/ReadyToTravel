package kr.ac.kopo.ReadyToTravel.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MemberInterceptor())
                .addPathPatterns("/member/profile/**")
                .addPathPatterns("/board/create")
                .addPathPatterns("/plan/updatePlan")
                .addPathPatterns("/plan/createPlan")
                .addPathPatterns("/group/**");

    }
}
