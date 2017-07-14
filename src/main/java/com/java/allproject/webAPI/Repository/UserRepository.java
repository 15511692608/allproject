package com.java.allproject.webAPI.Repository;

import com.java.allproject.webAPI.POJO.User;
import org.hibernate.dialect.Ingres9Dialect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;

/**
 * Created by admin on 2017/7/11.
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("select u from User u where u.phone=:phone")
    public User findByPhone(@Param("phone") String phone);
}
