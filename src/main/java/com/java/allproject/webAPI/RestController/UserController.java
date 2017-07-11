package com.java.allproject.webAPI.RestController;

import com.java.allproject.webAPI.POJO.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 2017/7/11.
 */
@RestController
public class UserController {

    @RequestMapping("register")
    public void register(){
        User user=new User();
    }
}
