package com.jasper;

import com.jasper.config.IgnoreUrlsConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author com
 * 6/21/2023  6:13 PM
 */
@SpringBootTest
public class TestConfig {

    @Test
    public void test(){
        IgnoreUrlsConfig ignoreUrlsConfig = new IgnoreUrlsConfig();
        System.out.println(ignoreUrlsConfig.getUrls());
    }
}
