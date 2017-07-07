package com.java.allproject.test.controller;

import com.java.allproject.test.dao.TestDao;
import com.java.allproject.test.dao.TestMongodbRep;
import com.java.allproject.test.model.TestModel;
import org.aspectj.weaver.ast.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Created by admin on 2017/7/5.
 */
@RestController
public class Testcontroller {
    private final static Logger log= LoggerFactory.getLogger(Testcontroller.class);
    @Autowired
    private TestDao testDao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private TestMongodbRep rep;
    @RequestMapping("findAll")
    public TestModel find(Long id){
        TestModel t=testDao.findAllById(id);
        log.info(rep.findOne(t.getId()).toString() );
        stringRedisTemplate.opsForValue().set("name",t.getName());
        return t;
    }
    @RequestMapping("save")
    public TestModel save(@ModelAttribute("TestModel") TestModel testModel){
        TestModel t=testDao.save(testModel);
        log.info(rep.insert(t).toString());
        stringRedisTemplate.opsForHash().put("name",t.getId(),t.getName());
        return t;
    }
}
