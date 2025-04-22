package com.GymManager.Backend.web.config;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClaudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dwupo9rvg",
                "api_key", "936787816378397",
                "api_secret", "zGdklEdWhEuSzbe5jTT8IPbOxew",
                "secure", true
        ));
    }
}
