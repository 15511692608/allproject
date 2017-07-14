package com.java.allproject.Interceptor;

import com.java.allproject.utils.JwtUtil;
import com.java.allproject.utils.ResultParam;
import com.java.allproject.webAPI.POJO.User;
import com.java.allproject.webAPI.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by admin on 2017/7/13.
 */
@Service
public class LoginInterceptor implements HandlerInterceptor {

    public static Logger logger= LoggerFactory.getLogger(LoginInterceptor.class);
    @Autowired
    private JwtUtil j;
    @Autowired
    private UserRepository repository;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String name=httpServletRequest.getRequestURI();
        logger.info("LoginInterceptor");
        String token=httpServletRequest.getParameter("token");
        if (token != null && (!"".equals(token.trim()) || !"null".equals(token))) {
            String u = j.parseJWT(token);
            if (u == null) {
                throw new Exception("request is err");
            }
            User user = ResultParam.objectMapper.readValue(u, User.class);
            if (repository.findOne(user.getId())==null) {
                throw new Exception("token is err");
            } else {
                return true;
            }
        } else {
            throw new Exception("no request");
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
