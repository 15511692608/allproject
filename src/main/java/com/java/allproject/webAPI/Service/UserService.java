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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
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
            throw new Exception("Phone is not found");
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
    public Map updateInfo(User user,String token)throws Exception{
        String u=jwtUtil.parseJWT(token);
        User old=ResultParam.objectMapper.readValue(u,User.class);
        if(user.getMail()!= null){
            old.setMail(user.getMail());
        }
        if(user.getMail()!= null) {
            old.setNickname(user.getNickname());
        }
        if(user.getPassword()!= null) {
            old.setPassword(user.getPassword());
        }
        if(user.getPhone()!= null) {
            old.setPhone(user.getPhone());
        }
        User newuser= userRepository.saveAndFlush(old);
        String jwt=jwtUtil.createJWT(String.valueOf(newuser.getId()), ResultParam.objectMapper.writeValueAsString(newuser));
        return ResultParam.resultMap(jwt);
    }
    public Map updatePass(String token,String pass,String newPass) throws Exception{
        String u=jwtUtil.parseJWT(token);
        User old=ResultParam.objectMapper.readValue(u,User.class);
        if(!MD5Util.endcodeMd5(pass).equals(String.valueOf(old.getPassword()))){
            throw new Exception("password is err");
        }
        old.setPassword(MD5Util.endcodeMd5(newPass));
        User newuser= userRepository.saveAndFlush(old);
        String jwt=jwtUtil.createJWT(String.valueOf(newuser.getId()), ResultParam.objectMapper.writeValueAsString(newuser));
        return ResultParam.resultMap(jwt);
    }

    /**
     *
     * @param number
     * @param size yechang
     * @param page yema
     * @return
     */
    public Map userList(int number,int size,int page){
        Sort sort=new Sort(Sort.Direction.DESC,"createTime");
        Pageable pageable=new PageRequest((page-1),size,sort) ;
        Page list=userRepository.findAll(pageable);
        return ResultParam.resultMap(list);
    }
    public Map userOne(String phone){
        return ResultParam.resultMap(userRepository.findByPhone(phone));
    }
}
