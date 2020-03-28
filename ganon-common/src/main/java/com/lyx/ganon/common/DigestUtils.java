package com.lyx.ganon.common;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description DigestUtils
 * @Author liyuxing
 * @Date 2019-12-13
 */
@Slf4j
public class DigestUtils {

    /**
     * 参考：Java Cryptography Architecture Standard Algorithm Name Documentation
     */
    public static final String ALGO_NAME_MD5 = "MD5";
    public static final String ALGO_NAME_SHA1 = "SHA-1";
    public static final String ALGO_NAME_SHA256 = "SHA-256";

    /**
     * 盐
     */
    private static final String SLAT = "liyustar";

    public static String getMD5(String str) {
        String base = str + SLAT;
        return digestAsHex(base.getBytes(), ALGO_NAME_MD5);
    }

    public static String getSHA1(String str) {
        String base = str + SLAT;
        return digestAsHex(base.getBytes(), ALGO_NAME_SHA1);
    }

    public static String getSHA256(String str) {
        String base = str + SLAT;
        return digestAsHex(base.getBytes(), ALGO_NAME_SHA256);
    }

    private static String digestAsHex(byte[] bytes, String algoName) {
        MessageDigest msgDigest = null;
        try {
            msgDigest = MessageDigest.getInstance(algoName);
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
        msgDigest.update(bytes);
        return byteToHexString(msgDigest.digest());
    }

    public static String getMD5WithSpringUtil(String str) {
        String base = str + SLAT;
        return org.springframework.util.DigestUtils.md5DigestAsHex(base.getBytes());
    }

    /**
     * 十六进制打印字节数组
     * @param bytes byte[]
     */
    private static String byteToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(String.format("%02x", bytes[i]));
        }
        return sb.toString();
    }
}

