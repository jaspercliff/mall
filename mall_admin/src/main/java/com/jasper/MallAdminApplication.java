package com.jasper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author com
 * 7/2/2023  4:37 PM
 */
@SpringBootApplication
@MapperScan("com.jasper.mapper")
@EnableTransactionManagement
@EnableConfigurationProperties
public class MallAdminApplication {
    public static void main(String[] args) {

        SpringApplication.run(MallAdminApplication.class, args);
    }
}
