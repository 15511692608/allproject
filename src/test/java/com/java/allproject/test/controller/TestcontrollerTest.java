package com.java.allproject.test.controller;

import com.java.allproject.test.model.TestModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by admin on 2017/7/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestcontrollerTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void find() throws Exception {
        TestModel t=new TestModel();
        t.setId(1L);
        t.setName("asd");
        Map m=t.map(t);
        stringRedisTemplate.opsForHash().putAll("TestModel",m);
        TestModel t2=new TestModel();
        t2.setId(2L);
        t2.setName("asd");
        Map m2=t.map(t);
        stringRedisTemplate.opsForHash().putAll("TestModel",m2);
        System.out.println(stringRedisTemplate.opsForHash().entries("TestModel"));
    }

}