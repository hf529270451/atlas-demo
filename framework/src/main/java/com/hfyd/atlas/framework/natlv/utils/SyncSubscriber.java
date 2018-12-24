package com.hfyd.atlas.framework.natlv.utils;

import rx.Subscriber;

public class SyncSubscriber<T> extends Subscriber<T> {

    private boolean isSuccess;
    private Throwable e;
    private T t;

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        isSuccess = false;
    }

    @Override
    public void onNext(T t) {
        isSuccess = true;
        this.t = t;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public Throwable getThrowable() {
        return e;
    }

    public T getValue(){
        return t;
    }
}
