package com.java.allproject.utils;


import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by mulder on 16/4/21.
 */
public class AesUtil {

    /** 密钥算法 */
    private static final String KEY_ALGORITHM = "AES";
    private static final int KEY_SIZE = 128;  //默认128位加密。如果超过128，则需要替换策略文件  http://www.cnblogs.com/SirSmith/p/4987064.html


    /**
     * 加密
     *
     * @param content 需要加密的内容
     * @param password  加密密码
     * @return
     */
    public static byte[] encrypt(String content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom random= SecureRandom.getInstance("SHA1PRNG");//需要自己手动设置
            random.setSeed(password.getBytes());
            kgen.init(128, random);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "Aes");
            Cipher cipher = Cipher.getInstance("Aes");// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**解密
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     */
    public static byte[] decrypt(byte[] content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("Aes");
            SecureRandom random= SecureRandom.getInstance("SHA1PRNG");//需要自己手动设置
            random.setSeed(password.getBytes());
            kgen.init(128, random);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "Aes");
            Cipher cipher = Cipher.getInstance("Aes");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(content);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 初始化密钥
     * @return
     * @throws Exception
     */
    public static String getKeyStr() throws Exception {
        return Base64.encodeBase64String(getKey().getEncoded());
    }

    /**
     * 获取密钥
     * @return
     * @throws Exception
     */
    public static Key getKey() throws Exception {
        //实例化
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        //AES 要求密钥长度为128位、192位或256位
        kg.init(KEY_SIZE);
        //生成密钥
        SecretKey secretKey = kg.generateKey();
        return secretKey;
    }


    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        String str = "gao1212";
        String key = "12345678";
        String encrytStr;
        byte[] encrytByte;

        byte[] byteRe = encrypt(str,key);

        //加密过的二进制数组转化成16进制的字符串
        encrytStr = parseByte2HexStr(byteRe);
        System.out.println("加密后："+encrytStr);

        //加密过的16进制的字符串转化成二进制数组
        encrytByte = parseHexStr2Byte(encrytStr);
        System.out.println("解密后："+new String(decrypt(encrytByte,key),"utf-8")  );


    }






    /**2进制转化成16进制
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }


    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

}
