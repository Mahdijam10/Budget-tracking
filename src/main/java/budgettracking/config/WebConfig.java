package budgettracking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // applies to all endpoints
                        .allowedOrigins("http://localhost:8080") // your React frontend
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // allowed HTTP methods
                        .allowedHeaders("*");
            }
        };
    }
}
