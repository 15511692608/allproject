package com.java.allproject.test.dao;

import com.java.allproject.test.model.TestModel;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by admin on 2017/7/6.
 */
public interface TestMongodbRep extends MongoRepository<TestModel, Long> {
}
