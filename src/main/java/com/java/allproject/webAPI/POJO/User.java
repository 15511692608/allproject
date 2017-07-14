package com.java.allproject.webAPI.POJO;

import lombok.Data;
import lombok.Generated;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by admin on 2017/7/11.
 */
@Data
@Table
@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(length = 11,unique = true,nullable = false)
    private String phone;
    private String nickname;
    private String mail;
    private String password;
    @Column(length=2,nullable = false )
    private int status=1;
    @Column(updatable = false,nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime=new Date();
}
