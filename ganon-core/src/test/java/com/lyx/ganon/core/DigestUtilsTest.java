package com.lyx.ganon.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DigestUtilsTest {

    @Test
    void getMD5() {
        System.out.println(DigestUtils.getMD5("liyustar").length());
    }
}