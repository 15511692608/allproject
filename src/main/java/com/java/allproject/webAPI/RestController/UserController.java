package com.java.allproject.webAPI.RestController;

import com.java.allproject.utils.ResultParam;
import com.java.allproject.webAPI.POJO.User;
import com.java.allproject.webAPI.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by admin on 2017/7/11.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("register")
    public Map register(User user){
        try {
           return userService.reg(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultParam.resultMapErr(null);
        }
    }

    @RequestMapping("/token")
    public Map token(String token) throws Exception {
        return userService.isToken(token);
    }
}
