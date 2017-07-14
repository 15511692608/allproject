package com.java.allproject.Interceptor;

import com.java.allproject.utils.ResultParam;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 *  统一异常处理
 * Created by admin on 2017/7/14.
 */
@ControllerAdvice
public class ExceptInterceptor {

        @ExceptionHandler(Exception.class)
        @ResponseBody
        public Map exceptionHadnler(Exception e){
            LoginInterceptor.logger.error(e.getMessage());
            return  ResultParam.resultMapErr(e.getMessage());
        }
}
