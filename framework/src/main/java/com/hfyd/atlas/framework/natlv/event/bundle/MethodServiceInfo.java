package com.hfyd.atlas.framework.natlv.event.bundle;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hfyd on 2018/8/31.
 */

public class MethodServiceInfo {
    public String methodName;
    public boolean isAsync;
    public String action;
    public String interceptorName;
    public TransactionType remoteType;
    public String commandName;

    private Map<String,Object> paramMap;

    public MethodServiceInfo() {
        paramMap = new HashMap<>();
    }

    public void putArg(String key,Object obj){
        paramMap.put(key,obj);
    }

    public Object getArg(String key){
        return paramMap.get(key);
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    @Override
    public String toString() {
        return "MethodServiceInfo{" +
                "methodName='" + methodName + '\'' +
                ", isAsync=" + isAsync +
                ", componentName='" + commandName + '\'' +
                ", action='" + action + '\'' +
                ", paramMap=" + paramMap +
                '}';
    }
}
