package com.java.allproject.Interceptor;

import com.java.allproject.utils.JwtUtil;
import com.java.allproject.utils.ResultParam;
import com.java.allproject.webAPI.POJO.User;
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

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String name=httpServletRequest.getRequestURI();
        logger.info(name);
        String token=httpServletRequest.getParameter("token");
        try {
            if (!"".equals(token.trim()) || !"null".equals(token)) {
                String u = j.parseJWT(token);
                if (u == null) {

                }
                User user = ResultParam.objectMapper.readValue(u, User.class);
                return true;

            } else {
                throw new Exception();
            }
        }catch (Exception e){
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().print(ResultParam.objectMapper.writeValueAsString(ResultParam.resultMapErr(null)));
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
