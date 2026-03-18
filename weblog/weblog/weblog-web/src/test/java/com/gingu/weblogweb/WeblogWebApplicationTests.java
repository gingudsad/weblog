package com.gingu.weblogweb;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class WeblogWebApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testLog(){
        log.info("info级别日志");
        log.warn("warn级别日志");
        log.error("err错误日志");
        String author="gingu";
        log.info("占位符日志,作者:{}",author);
    }

}
