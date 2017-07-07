package com.java.allproject.test.model;

import lombok.*;

import javax.persistence.*;

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


}
