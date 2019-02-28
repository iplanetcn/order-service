package com.phenix.util;

import java.util.Random;
import java.util.UUID;

public class KeyUtils {
    /**
     * 生成唯一的主键(格式:时间+随机数)
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        System.currentTimeMillis();
        Integer number = random.nextInt(900000) + 1000000;
        return System.currentTimeMillis() + String.valueOf(number);
    }

    /**
     * 生成唯一识别码
     */
    public static synchronized String genUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
