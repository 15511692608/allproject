package com.java.allproject.webAPI.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.java.allproject.utils.JwtUtil;
import com.java.allproject.utils.MD5Util;
import com.java.allproject.utils.ResultParam;
import com.java.allproject.webAPI.POJO.User;
import com.java.allproject.webAPI.Repository.UserRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017/7/11.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Value("${web.aes}")
    private String aes;

    public Map reg(User user) throws Exception {
        if(user.getPhone()==null || user.getPhone().length()!=11){
            throw new Exception();
        }
        user.setPassword(MD5Util.endcodeMd5(user.getPassword()));
        User u= userRepository.save(user);
        String jwt=jwtUtil.createJWT(String.valueOf(u.getId()), ResultParam.objectMapper.writeValueAsString(u));
        return ResultParam.resultMap(jwt);
    }
    public Map isToken(String token) throws Exception {
        String user=jwtUtil.parseJWT(token);
        return ResultParam.resultMap(ResultParam.objectMapper.readValue(user,User.class));
    }
}
