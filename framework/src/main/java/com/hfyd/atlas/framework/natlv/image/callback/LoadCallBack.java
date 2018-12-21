package com.hfyd.atlas.framework.natlv.image.callback;

/**
 * Created by hfyd on 2018/3/8.
 * LoadCallBack
 */
public abstract class LoadCallBack<T> {

    private int width;
    private int height;

    public LoadCallBack() {
    }

    public LoadCallBack(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public abstract void onLoadSuccess(T t);

    public abstract void onLoadError();

}
