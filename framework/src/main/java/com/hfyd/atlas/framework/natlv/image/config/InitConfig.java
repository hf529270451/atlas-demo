package com.hfyd.atlas.framework.natlv.image.config;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by hfyd on 2018/3/7.
 * 初始化配置
 */
public class InitConfig {


    private Context context;
    private Object model;
    private Drawable placeHolderDrawable;
    private int placeHolderResId;
    private boolean isBitmap;

    private String cacheType = "";
    public static final String CacheType_MEMORY = "MemoryCache";
    public static final String CacheType_DISK = "DiskCache";
    public static final String CacheType_NONE = "None";
    public static final String CacheType_All = "All";

    @StringDef({CacheType_MEMORY, CacheType_DISK, CacheType_NONE, CacheType_All})
    @Retention(RetentionPolicy.SOURCE)
    public @interface CacheTypeSelfConstraint {
    }

    private String scaleType = "";
    public static final String ScaleType_Matrix = "Matrix";
    public static final String ScaleType_Center = "Center";
    public static final String ScaleType_CenterInside = "CenterInside";
    public static final String ScaleType_CenterCrop = "CenterCrop";
    public static final String ScaleType_FitCenter = "FitCenter";
    public static final String ScaleType_FitStart = "FitStart";
    public static final String ScaleType_FitEnd = "FitEnd";
    public static final String ScaleType_FitXY = "FitXY";
    public static final String ScaleType_CircleCrop = "CircleCrop";

    @StringDef({ScaleType_Matrix, ScaleType_Center, ScaleType_CenterInside, ScaleType_CenterCrop
            , ScaleType_FitCenter, ScaleType_FitStart, ScaleType_FitEnd, ScaleType_FitXY, ScaleType_CircleCrop})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ScaleTypeSelfConstraint {
    }

    public String transformType = "";
    public static final String TransformType_CropCircle = "CropCircle";

    @StringDef({TransformType_CropCircle})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TransformTypeSelfConstraint {
    }


    public InitConfig(Context context, Object model, String cacheType, Drawable placeHolderDrawable, int placeHolderResId
            , boolean isBitmap, String scaleType, String transformType) {
        this.context = context;
        this.cacheType = cacheType;
        this.model = model;
        this.placeHolderDrawable = placeHolderDrawable;
        this.placeHolderResId = placeHolderResId;
        this.isBitmap = isBitmap;
        this.scaleType = scaleType;
        this.transformType = transformType;

    }

    public String getTransformType() {
        return transformType;
    }

    public boolean isBitmap() {
        return isBitmap;
    }

    public Context getContext() {
        return context;
    }

    public Object getModel() {
        return model;
    }

    public Drawable getPlaceHolderDrawable() {
        return placeHolderDrawable;
    }

    public int getPlaceHolderResId() {
        return placeHolderResId;
    }

    public
    @CacheTypeSelfConstraint
    String getCacheType() {
        return cacheType;
    }

    public
    @ScaleTypeSelfConstraint
    String getScaleType() {
        return scaleType;
    }

    public static class InitConfigBuild {

        @CacheTypeSelfConstraint
        private String cacheType;
        @ScaleTypeSelfConstraint
        private String scaleType;
        @TransformTypeSelfConstraint
        private String transformType;

        private Context context;
        private Object model;
        private Drawable placeHolderDrawable;
        private int placeHolderResId;
        private boolean isBitmap;

        public InitConfig build() {
            if (this.context == null) {
                throw new IllegalStateException("Context must not null");
            }
            return new InitConfig(context, model, cacheType, placeHolderDrawable, placeHolderResId, isBitmap
                    , scaleType,transformType);
        }

        public void transformType(String transformType) {
            this.transformType = transformType;
        }

        public InitConfigBuild asBitmap() {
            isBitmap = true;
            return this;
        }

        public InitConfigBuild setPlaceHolderDrawable(Drawable placeHolderDrawable) {
            this.placeHolderDrawable = placeHolderDrawable;
            return this;
        }

        public InitConfigBuild setPlaceHolderResId(int placeHolderResId) {
            this.placeHolderResId = placeHolderResId;
            return this;
        }

        public InitConfigBuild setPath(Object model) {
            this.model = model;
            return this;
        }

        public InitConfigBuild setContext(Context context) {
            this.context = context;
            return this;
        }

        public InitConfigBuild setCacheType(@CacheTypeSelfConstraint String cacheType) {
            this.cacheType = cacheType;
            return this;
        }

        public InitConfigBuild setScaleType(@ScaleTypeSelfConstraint String scaleType) {
            this.scaleType = scaleType;
            return this;
        }
    }
}
