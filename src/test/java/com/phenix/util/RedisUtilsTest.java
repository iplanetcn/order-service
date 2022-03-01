package com.phenix.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class RedisUtilsTest {
    @Resource
    private RedisUtils redisUtils;

    @BeforeEach
    public void setUp() {
        boolean isSuccess = redisUtils.set("user_name", "John Lee");
        assertTrue(isSuccess);
    }

    @AfterEach
    public void tearDown() {
        redisUtils.del("user_name");
    }

    @Test
    public void getUserName() {
        String user_nme = (String) redisUtils.get("user_name");
        assertEquals("John Lee", user_nme);
    }


}