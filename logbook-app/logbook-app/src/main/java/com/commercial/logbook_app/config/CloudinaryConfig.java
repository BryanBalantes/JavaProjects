package com.commercial.logbook_app.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        final Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dg6ixluq1");
        config.put("api_key", "684252976875825");
        config.put("api_secret", "NTBh8bot6AW0OxjFbUGwSdzXKjM");
        return new Cloudinary(config);
    }
}
