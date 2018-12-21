package com.hfyd.atlas.framework.natlv.parse;

import com.google.gson.Gson;

/**
 * Author: hfyd
 * Date: 2018/12/19
 * Description:
 */
public class JsonUtils {

    public static Gson gson = new Gson();

    public static <T> T fromJson(String json,Class<T> clazz){
        return gson.fromJson(json,clazz);
    }

    public static String toJson(Object obj){
        return gson.toJson(obj);
    }
}
