package com.chungxangla.demo.config;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){
        corsRegistry.addMapping("/**")
        .allowedOrigins("http:://localhost:60704")
        .allowedMethods("*")
        .allowedHeaders("*")
        .allowCredentials(true);
    }
}
