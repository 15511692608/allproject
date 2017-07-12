package com.java.allproject.utils;

/**
 * Created by mulder on 2015/9/18.
 */
import java.security.MessageDigest;

public class MD5Util {
    public final static String endcodeMd5(String s) {
        // 用作十六进制的数组.
        byte hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");// 使用MD5加密
            byte[] strTemp = s.getBytes("UTF-8");// 把传入的字符串转换成字节数组
            mdTemp.update(strTemp);//
            byte[] md = mdTemp.digest();
            int j = md.length;
            byte str[] = new byte[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);// 返回加密后的字符串.
        } catch (Exception e) {
            return null;
        }
    }
    public static void main(String[] args) {
        System.out.println(MD5Util.endcodeMd5("20121221"));
        System.out.println(MD5Util.endcodeMd5("加密"));
    }
}
