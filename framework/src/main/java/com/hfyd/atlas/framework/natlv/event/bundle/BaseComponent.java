/*package com.hfyd.atlas.framework.natlv.event.bundle;

import android.text.TextUtils;
import android.util.Log;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.IComponent;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

*//**
 * Created by hfyd on 2018/8/29.
 *//*
public class BaseComponent implements IComponent {

    private Map<String, MethodInfo> methodMap = new HashMap<>();
    private String componentName;

    public BaseComponent() {
        init();
    }

    @Override
    public String getName() {
        return componentName;
    }

    private void init() {
        Annotation[] classAnnos = getClass().getDeclaredAnnotations();
        for (Annotation annotation : classAnnos) {
            if (annotation instanceof ComponentName) {
                componentName = ((ComponentName) annotation).value();
            }
        }

        Method[] methods = getClass().getDeclaredMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation anno : annotations) {
                if (anno instanceof Action && !TextUtils.isEmpty(((Action) anno).value())) {
                    MethodInfo info = new MethodInfo();
                    info.method = method;
                    methodMap.put(((Action) anno).value(), info);
                }
            }
        }
    }

    @Override
    public boolean onCall(CC cc) {
        return dispatchCall(cc);
    }

    private boolean dispatchCall(CC cc) {
        MethodInfo methodInfo = methodMap.get(cc.getActionName());
        if (methodInfo != null) {
            try {
                Method method = methodInfo.method;
                if (method != null) {
                    return (boolean) method.invoke(this, cc);
                }
            } catch (Exception e) {
//                PrintLog.error(e);
                Log.e("component",Log.getStackTraceString(e));
            }
        }
        return false;
    }

    *//*protected void sendSuccessResult(String callId) {
        sendSuccessResult(callId,null);
    }

    protected void sendSuccessResult(String callId, String successKey, Object successValue) {
        Map<String, Object> data = new HashMap<>(2);
        data.put(successKey, successValue);
        sendSuccessResult(callId,data);
    }

    protected void sendSuccessResult(String callId, Map<String, Object> map) {
        CC.sendCCResult(callId, CCResult.success(map));
    }

    protected void sendErrorResult(String callId, String message) {
        CC.sendCCResult(callId, CCResult.error(message));
    }

    protected void sendErrorResult(String callId, String errorKey, Object errorValue) {
        CC.sendCCResult(callId, CCResult.error(errorKey, errorValue));
    }*//*
}*/
