package com.java.allproject.test.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017/7/5.
 */
@Data
@Entity
@Table(name="test")
public class TestModel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name")
    private String name;

    public Map map(TestModel testModel){
        Map map=new HashMap();
        map.put("id",testModel.getId().toString());
        map.put("name",testModel.getName());
        return map;
    }
}
