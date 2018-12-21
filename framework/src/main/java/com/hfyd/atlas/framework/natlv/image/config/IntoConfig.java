package com.hfyd.atlas.framework.natlv.image.config;

import android.widget.ImageView;

import com.hfyd.atlas.framework.natlv.image.callback.LoadCallBack;


/**
 * Created by hfyd on 2018/3/8.
 */
public class IntoConfig {

    private ImageView targetImageView;

    private LoadCallBack loadCallBack;

    private IntoConfig(ImageView targetImageView, LoadCallBack loadCallBack) {
        this.targetImageView = targetImageView;
        this.loadCallBack = loadCallBack;
    }

    public ImageView getTargetImageView() {
        return targetImageView;
    }

    public LoadCallBack getLoadCallBack() {
        return loadCallBack;
    }

    public static class IntoConfigBuild {
        private ImageView targetImageView;
        private LoadCallBack loadCallBack;

        public IntoConfig build() {
            return new IntoConfig(targetImageView, this.loadCallBack);
        }

        public void setLoadCallBack(LoadCallBack loadCallBack) {
            this.loadCallBack = loadCallBack;
        }

        public void setTargetImageView(ImageView targetImageView) {
            this.targetImageView = targetImageView;
        }
    }
}
