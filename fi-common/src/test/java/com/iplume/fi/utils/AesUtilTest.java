package com.iplume.fi.utils;

import junit.framework.TestCase;
import lombok.extern.java.Log;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

/**
 * Aes测试类.
 *
 * @author: lingchen
 * @date: 2021/8/19
 */
@Log
public class AesUtilTest extends TestCase {

    @Test
    public void testAesEncrypt() throws Exception {

        byte[] bytes = AesUtil.initKey();

        // 使用密钥生成器 KeyGenerator 生成的 16 字节随机密钥的 hex 字符串，使用时解 hex 得到二进制密钥
        // String aesKey = Hex.encodeHexString(bytes);
        String aesKey = Hex.encodeHexString("iplume_key_12345".getBytes());
        log.info("aesKey: " + aesKey);

        String content = "Aa123456";
        log.info("加密前: " + content);

        // 加密.
        String encrypt = AesUtil.aesEncrypt(content, aesKey);
        log.info("加密后: " + encrypt);
        log.info("加密串的长度: " + encrypt.length());

        // 解密.
        String decrypt = AesUtil.aesDecrypt(encrypt, aesKey);
        log.info("解密后: " + decrypt);
    }

}