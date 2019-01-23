package com.ray.common.mybatis.common.redis.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

/**
 *  加密工具类，包含MD5,BASE64,SHA,CRC32
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 4/29/2018.
 */
public class CryptoUtil {

    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * HMAC-SHA1 加密;使用密钥
     * @param data 加密数据
     * @param key 密钥
     * @return
     */
    public static byte[] hamcsha1(byte[] data, byte[] key) {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key, "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            return mac.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * MD5加密
     *
     * @param bytes
     *            an array of byte.
     * @return a {@link String} object.
     */
    public static String encodeMD5(final byte[] bytes) {
        return DigestUtils.md5Hex(bytes);
    }

    /**
     * MD5加密，默认UTF-8
     *
     * @param str
     *            a {@link String} object.
     * @return a {@link String} object.
     */
    public static String encodeMD5(final String str) {
        return encodeMD5(str, DEFAULT_CHARSET);
    }

    /**
     * MD5加密
     *
     * @param str
     *            a {@link String} object.
     * @param charset
     *            a {@link String} object.
     * @return a {@link String} object.
     */
    public static String encodeMD5(final String str, final String charset) {
        if (str == null) {
            return null;
        }
        try {
            byte[] bytes = str.getBytes(charset);
            return encodeMD5(bytes);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * SHA加密
     *
     * @param bytes
     *            an array of byte.
     * @return a {@link String} object.
     */
    public static String encodeSHA(final byte[] bytes) {
        return DigestUtils.sha512Hex(bytes);
    }

    /**
     * SHA加密
     *
     * @param str
     *            a {@link String} object.
     * @param charset
     *            a {@link String} object.
     * @return a {@link String} object.
     */
    public static String encodeSHA(final String str, final String charset) {
        if (str == null) {
            return null;
        }
        try {
            byte[] bytes = str.getBytes(charset);
            return encodeSHA(bytes);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * SHA加密,默认utf-8
     *
     * @param str
     *            a {@link String} object.
     * @return a {@link String} object.
     */
    public static String encodeSHA(final String str) {
        return encodeSHA(str, DEFAULT_CHARSET);
    }

    /**
     * BASE64加密
     *
     * @param bytes
     *            an array of byte.
     * @return a {@link String} object.
     */
    public static String encodeBASE64(final byte[] bytes) {
        return new String(Base64.encodeBase64String(bytes));
    }

    /**
     * BASE64加密
     *
     * @param str
     *            a {@link String} object.
     * @param charset
     *            a {@link String} object.
     * @return a {@link String} object.
     */
    public static String encodeBASE64(final String str, String charset) {
        if (str == null) {
            return null;
        }
        try {
            byte[] bytes = str.getBytes(charset);
            return encodeBASE64(bytes);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * BASE64加密,默认UTF-8
     *
     * @param str
     *            a {@link String} object.
     * @return a {@link String} object.
     */
    public static String encodeBASE64(final String str) {
        return encodeBASE64(str, DEFAULT_CHARSET);
    }

    /**
     * BASE64解密,默认UTF-8
     *
     * @param str
     *            a {@link String} object.
     * @return a {@link String} object.
     */
    public static String decodeBASE64(String str) {
        return decodeBASE64(str, DEFAULT_CHARSET);
    }

    /**
     * BASE64解密
     *
     * @param str
     *            a {@link String} object.
     * @param charset
     *            字符编码
     * @return a {@link String} object.
     */
    public static String decodeBASE64(String str, String charset) {
        try {
            return new String(Base64.decodeBase64(str.getBytes()), charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * CRC32字节校验
     *
     * @param bytes
     *            an array of byte.
     * @return a {@link String} object.
     */
    public static String crc32(byte[] bytes) {
        CRC32 crc32 = new CRC32();
        crc32.update(bytes);
        return Long.toHexString(crc32.getValue());
    }

    /**
     * CRC32字符串校验
     *
     * @param str
     *            a {@link String} object.
     * @param charset
     *            a {@link String} object.
     * @return a {@link String} object.
     */
    public static String crc32(final String str, String charset) {
        try {
            byte[] bytes = str.getBytes(charset);
            return crc32(bytes);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * CRC32字符串校验,默认UTF-8编码读取
     *
     * @param str
     *            a {@link String} object.
     * @return a {@link String} object.
     */
    public static String crc32(final String str) {
        return crc32(str, DEFAULT_CHARSET);
    }

    /**
     * CRC32流校验
     *
     * @param input
     *            a {@link InputStream} object.
     * @return a {@link String} object.
     */
    public static String crc32(InputStream input) {
        CRC32 crc32 = new CRC32();
        CheckedInputStream checkInputStream = null;
        int test = 0;
        try {
            checkInputStream = new CheckedInputStream(input, crc32);
            do {
                test = checkInputStream.read();
            } while (test != -1);
            return Long.toHexString(crc32.getValue());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * CRC32文件唯一校验
     *
     * @param file
     *            a {@link File} object.
     * @return a {@link String} object.
     */
    public static String crc32(File file) {
        InputStream input = null;
        try {
            input = new BufferedInputStream(new FileInputStream(file));
            return crc32(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(input);
        }
    }

    /**
     * CRC32文件唯一校验
     *
     * @param url
     *            a {@link URL} object.
     * @return a {@link String} object.
     */
    public static String crc32(URL url) {
        InputStream input = null;
        try {
            input = url.openStream();
            return crc32(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(input);
        }
    }
}