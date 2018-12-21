package com.hfyd.atlas.framework.natlv.event.bundle;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.text.TextUtils;


import com.hfyd.atlas.framework.natlv.event.bundle.atlas.AtlasRemoteHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by hfyd on 2018/8/30.
 */

public class RxComponentCaller {

    private RemoteHandler mRemoteHandler;

    public RxComponentCaller(Activity activity) {
        this.mRemoteHandler = new AtlasRemoteHandler(activity);
    }

    public void setRemoteHandler(RemoteHandler remoteHandler) {
        this.mRemoteHandler = remoteHandler;
    }

    @SuppressWarnings("unchecked")
    public <T> T create(final Class<T> service) {
        validateServiceInterface(service);
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, @Nullable Object[] args)
                            throws Throwable {
                        MethodServiceInfo info = parseMethodAnno(method, args);
                        validateMethodServiceInfo(info);
                        return realExecuteComponentCall(info);
                    }
                });
    }

    private Observable<Object> realExecuteComponentCall(final MethodServiceInfo info) {
        return Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(final Subscriber<? super Object> subscriber) {
                try {
                    mRemoteHandler.execute(subscriber, info);
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }


    private MethodServiceInfo parseMethodAnno(Method method, Object[] args) {
        MethodServiceInfo info = new MethodServiceInfo();
        info.methodName = method.getName();
        Async async = method.getAnnotation(Async.class);
        if (async != null) {
            info.isAsync = true;
            info.componentName = async.componentName();
            info.action = async.action();
        }

        Sync aync = method.getAnnotation(Sync.class);
        if (aync != null) {
            info.isAsync = false;
            info.componentName = aync.componentName();
            info.action = aync.action();
        }

        Interceptor interceptor = method.getAnnotation(Interceptor.class);
        if (interceptor != null)
            info.interceptorName = interceptor.name();

        TransactionLevel level = method.getAnnotation(TransactionLevel.class);
        if (level != null){
            info.remoteType = level.value();
        }

        Annotation[][] parameterAnnos = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnos.length; i++) {
            Annotation[] param = parameterAnnos[i];
            if (param.length > 0) {
                for (Annotation anno : param) {
                    if (anno instanceof Param) {
                        String argKey = ((Param) anno).value();
                        Object arg = args[i];
                        if ("commandName".equals(argKey) && arg instanceof String) {
                            info.commandName = (String) arg;
                        } else {
                            info.putArg(argKey, arg);
                        }
                    }
                }
            }
        }

        return info;
    }


    private void validateMethodServiceInfo(MethodServiceInfo info) {
        if (TextUtils.isEmpty(info.componentName))
            throw new RuntimeException("ComponentName must not null,Method: " + info.methodName);
        if (TextUtils.isEmpty(info.action))
            throw new RuntimeException("Action must not null,Method: " + info.methodName);
    }

    private <T> void validateServiceInterface(Class<T> service) {
        if (!service.isInterface()) {
            throw new IllegalArgumentException("API declarations must be interfaces.");
        }
        // Prevent API interfaces from extending other interfaces. This not only avoids a bug in
        // Android (http://b.android.com/58753) but it forces composition of API declarations which is
        // the recommended pattern.
        if (service.getInterfaces().length > 0) {
            throw new IllegalArgumentException("API interfaces must not extend other interfaces.");
        }
    }
}
