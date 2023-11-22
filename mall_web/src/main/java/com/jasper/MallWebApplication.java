package com.jasper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author com
 * 6/18/2023  11:30 PM
 */
@SpringBootApplication
@MapperScan("com.jasper.mapper")
@EnableConfigurationProperties
public class MallWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallWebApplication.class, args);
    }
}
