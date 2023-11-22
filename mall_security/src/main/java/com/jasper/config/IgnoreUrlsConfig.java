package com.jasper.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author com
 * 6/21/2023  6:02 PM
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "security.ignored")
@EnableConfigurationProperties
public class IgnoreUrlsConfig {
    private List<String> urls = new ArrayList<>();
}
