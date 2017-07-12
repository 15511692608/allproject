package com.java.allproject.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    @Value("${web.profiles}")
    private String profiles;
    @Value("${web.JWT_KEY}")
    private String key;

    private static Long ttlMillis=72000*3600L;
    /**
     * 由字符串生成加密key
     * @return
     */
    public SecretKey generalKey(){
        String stringKey = profiles+key;
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 创建jwt
     * @param id
     * @param subject
     * @return
     * @throws Exception
     */
    public String createJWT(String id, String subject) throws Exception {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey key = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .signWith(signatureAlgorithm, key);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * 解密jwt
     * @param jwt
     * @return
     * @throws Exception
     */
    public String parseJWT(String jwt) throws Exception{
        SecretKey key = generalKey();
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt).getBody();
        String user = claims.getSubject();
        if(claims.getExpiration().before(new Date())){
            return null;
        }
        return user;
    }


    public static void main(String[]args) throws Exception {
        JwtUtil j=new JwtUtil();
        Map map=new HashMap();
        String s=j.createJWT("4","{\"id\":4,\"phone\":\"15511692600\",\"nickname\":null,\"mail\":null,\"password\":\"3d9188577cc9bfe9291ac66b5cc872b7\",\"status\":1}");
        System.out.println(s);
        //eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI0IiwiaWF0IjoxNDk5ODQ5Mzc5LCJzdWIiOiJ7XCJpZFwiOjQsXCJwaG9uZVwiOlwiMTU1MTE2OTI2MDBcIixcIm5pY2tuYW1lXCI6bnVsbCxcIm1haWxcIjpudWxsLFwicGFzc3dvcmRcIjpcIjNkOTE4ODU3N2NjOWJmZTkyOTFhYzY2YjVjYzg3MmI3XCIsXCJzdGF0dXNcIjoxfSIsImV4cCI6MTUwMDEwODU3OX0.kBe1rfCA_EHWCUwsIlwpdtJkRhh7xn3Liu3GmUCIWuw
        //eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI0IiwiaWF0IjoxNDk5ODQ5MzQ0LCJzdWIiOiJ7XCJpZFwiOjQsXCJwaG9uZVwiOlwiMTU1MTE2OTI2MDBcIixcIm5pY2tuYW1lXCI6bnVsbCxcIm1haWxcIjpudWxsLFwicGFzc3dvcmRcIjpcIjNkOTE4ODU3N2NjOWJmZTkyOTFhYzY2YjVjYzg3MmI3XCIsXCJzdGF0dXNcIjoxfSIsImV4cCI6MTUwMDEwODU0NH0.8uWn6y_9Gb4AQVWZFKWUHWaCgj3YnD7RcUYMVpI0fx4

        System.out.println(j.parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI0IiwiaWF0IjoxNDk5ODUwMTUwLCJzdWIiOiJ7XCJpZFwiOjQsXCJwaG9uZVwiOlwiMTU1MTE2OTI2MDBcIixcIm5pY2tuYW1lXCI6bnVsbCxcIm1haWxcIjpudWxsLFwicGFzc3dvcmRcIjpcIjNkOTE4ODU3N2NjOWJmZTkyOTFhYzY2YjVjYzg3MmI3XCIsXCJzdGF0dXNcIjoxfSIsImV4cCI6MTUwMDEwOTM1MH0.XEfvCe5vA_iUSAM7Bp_AkaPEewNmxBlvIx-yFWBNvh0"));

    }
}