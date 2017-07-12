package com.java.allproject.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017/7/12.
 */
public class ResultParam {

    public static ObjectMapper objectMapper=new ObjectMapper();

    public static Map resultMap(Object result,String code,String msg){
        Map map=new HashMap();
        map.put("result",result);
        map.put("code",code);
        map.put("msg",msg);
        return map;
    }

    public static Map resultMap(Object result){
        Map map=new HashMap();
        map.put("result",result);
        map.put("code","C00");
        map.put("msg","");
        return map;
    }

    public static Map resultMapErr(Object result){
        Map map=new HashMap();
        map.put("result",result);
        map.put("code","C99");
        map.put("msg","");
        return map;
    }
}
