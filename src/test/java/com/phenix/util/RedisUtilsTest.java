package com.phenix.util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilsTest {
    @Resource
    private RedisUtils redisUtils;

    @Before
    public void setUp() {
        boolean isSuccess = redisUtils.set("user_name", "John Lee");
        Assert.assertTrue(isSuccess);
    }

    @After
    public void tearDown() {
        redisUtils.del("user_name");
    }

    @Test
    public void getUserName() {
        String user_nme = (String) redisUtils.get("user_name");
        Assert.assertEquals("John Lee", user_nme);
    }


}