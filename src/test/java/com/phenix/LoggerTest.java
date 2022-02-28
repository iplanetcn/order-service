package com.phenix;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class LoggerTest {
    @Test
    public void testLogger() {
        String name = "john";
        String password = "pwd";

        log.info("name {},password {}", name, password);
        log.info("name " + name + ",password " + password);
        log.debug("debug...");
        log.info("info...");
        log.warn("warn...");
        log.error("error...");
    }
}
