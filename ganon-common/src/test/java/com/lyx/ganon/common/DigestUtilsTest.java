package com.lyx.ganon.common;

import org.junit.jupiter.api.Test;

class DigestUtilsTest {

    @Test
    void getMD5() {
        System.out.println(DigestUtils.getMD5("liyustar").length());
    }
}