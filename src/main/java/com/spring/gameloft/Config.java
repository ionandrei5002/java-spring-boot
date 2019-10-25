package com.spring.gameloft;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.spring.gameloft")
@Data
public class Config {
    private String defaultMessage;
}
