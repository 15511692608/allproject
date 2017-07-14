package com.java.allproject.webAPI.RestController;

import com.java.allproject.utils.ResultParam;
import com.java.allproject.webAPI.POJO.User;
import com.java.allproject.webAPI.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by admin on 2017/7/11.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("register")
    public Map register(User user) throws Exception {
           return userService.reg(user);
    }

    @RequestMapping("/token")
    public Map token(String token) throws Exception {
        return userService.isToken(token);
    }

    @RequestMapping("updateInfo")
    public Map updateInfo(String token,User user)throws Exception{
        return userService.updateInfo(user,token);
    }

    @RequestMapping("updatePass")
    public Map updatePass(String token,String pass,String newPass)throws Exception{
        return userService.updatePass(token,pass,newPass);
    }

    @RequestMapping("userList")
    public Map userList(int number, int size,int page){
        return userService.userList(number,size,page);
    }
    @RequestMapping("userOne")
    public Map userOne(String phone){
        return userService.userOne(phone);
    }
}
