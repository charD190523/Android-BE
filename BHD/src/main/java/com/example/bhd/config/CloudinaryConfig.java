package com.example.bhd.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dcwkpycii",
                "api_key", "416324497149224",
                "api_secret", "zEp3oUWNtnkS9nAbq8aNkR2qTxk",
                "secure", true
        ));
    }
}
