package com.cognizant.springlearn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The @Transactional transaction manager is auto-configured by Spring Boot
 * (DataSourceTransactionManager over the embedded H2). This config just opens
 * CORS so the Angular dev server (http://localhost:4200) can call the API.
 */
@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}
