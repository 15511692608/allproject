package com.java.allproject.webAPI.Repository;

import com.java.allproject.webAPI.POJO.User;
import org.hibernate.dialect.Ingres9Dialect;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by admin on 2017/7/11.
 */
public interface UserRepository extends JpaRepository<User,Integer> {

}
