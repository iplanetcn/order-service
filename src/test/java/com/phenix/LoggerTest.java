package com.phenix;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
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
