package com.java.allproject.webAPI.Service;

import com.java.allproject.webAPI.POJO.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 2017/7/11.
 */
public class UserService {
    @Value("${web.ase}")
    private String ase;

    public void reg(){

    }

}
