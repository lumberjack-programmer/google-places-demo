package com.google.places.googleplacesdemo;

import io.github.cdimascio.dotenv.Dotenv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GooglePlacesDemoApplication {
    private static final Logger logger = LoggerFactory.getLogger(GooglePlacesDemoApplication.class);

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String googleApiKey = dotenv.get("GOOGLE_API_KEY");
        if(googleApiKey == null) {
            logger.error("Google API Key not found in .env file");
            throw new RuntimeException("Google API Key not found in .env file");
        }
        logger.info("Setting google.api.key system property");
        System.setProperty("google.api.key", googleApiKey);
        SpringApplication.run(GooglePlacesDemoApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
