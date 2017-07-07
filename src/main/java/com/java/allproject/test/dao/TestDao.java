package com.java.allproject.test.dao;

import com.java.allproject.test.model.TestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by admin on 2017/7/5.
 */
public interface TestDao extends JpaRepository<TestModel,Long> {
     public TestModel save (TestModel testModel);

     public TestModel findAllById(Long id);
}
