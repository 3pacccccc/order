package com.immoc.server.utils;

import java.util.Random;

public class KeyUtil {
    /**
     * 生成唯一的主键
     * 格式：时间+随机数
     */
    public static synchronized String genUniqueKey() {  // synchronized表示在多线程的情况下，避免生成相同的key
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;  //生成6位的随机数
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
