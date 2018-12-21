package com.hfyd.atlas.framework.natlv.image.loader;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.hfyd.atlas.framework.natlv.image.callback.LoadCallBack;
import com.hfyd.atlas.framework.natlv.image.config.InitConfig;
import com.hfyd.atlas.framework.natlv.image.config.IntoConfig;
import com.hfyd.atlas.framework.natlv.utils.TUtils;

/**
 * Created by hfyd on 2018/3/7.
 * GlideLoader
 */
public class GlideLoader implements ILoader {

    private RequestBuilder builder;
    private boolean isBitmap;

    @Override
    public void init(InitConfig initConfig) {
        String cacheType = initConfig.getCacheType();
        RequestOptions options = new RequestOptions();

        if (initConfig.getPlaceHolderDrawable() != null) {
            options.placeholder(initConfig.getPlaceHolderDrawable());
        }

        if (initConfig.getPlaceHolderResId() != 0) {
            options.placeholder(initConfig.getPlaceHolderResId());
        }

        if (!TextUtils.isEmpty(cacheType)) {
            switch (cacheType) {
                case InitConfig.CacheType_DISK:
                    break;
                case InitConfig.CacheType_MEMORY:
                    break;
                case InitConfig.CacheType_NONE:
                    options.diskCacheStrategy(DiskCacheStrategy.NONE);
                    break;
                case InitConfig.CacheType_All:
                    options.diskCacheStrategy(DiskCacheStrategy.ALL);
                    break;
                default:
                    options.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
            }
        }


        String scaleType = initConfig.getScaleType();
        if(!TextUtils.isEmpty(scaleType)){
            switch (scaleType) {
                case InitConfig.ScaleType_Center:
                    break;
                case InitConfig.ScaleType_CenterInside:
                    options.centerInside();
                    break;
                case InitConfig.ScaleType_Matrix:
                    break;
                case InitConfig.ScaleType_CenterCrop:
                    options.centerCrop();
                    break;
                case InitConfig.ScaleType_FitCenter:
                    options.fitCenter();
                    break;
                case InitConfig.ScaleType_FitEnd:
                    break;
                case InitConfig.ScaleType_FitStart:
                    break;
                case InitConfig.ScaleType_CircleCrop:
                    options.circleCrop();
                    break;
                default:
            }
        }

        isBitmap = initConfig.isBitmap();
        if (initConfig.isBitmap()) {
            builder = Glide.with(initConfig.getContext()).asBitmap().load(initConfig.getModel()).apply(options);
        } else {
            builder = Glide.with(initConfig.getContext()).load(initConfig.getModel()).apply(options);
        }

    }

    @Override
    public void into(IntoConfig intoConfig) {
        if (intoConfig.getTargetImageView() != null) {
            builder.into(intoConfig.getTargetImageView());
        }

        LoadCallBack loadCallBack = intoConfig.getLoadCallBack();
        if (loadCallBack != null) {
            intoCallBack(loadCallBack);
        }
    }

    private void intoCallBack(final LoadCallBack loadCallBack) {
        Class actualClass = TUtils.getActualClass(loadCallBack.getClass());
        //需要重构
        int width = loadCallBack.getWidth();
        int height = loadCallBack.getHeight();

        if (actualClass == Drawable.class) {
            builder.into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                    loadCallBack.onLoadSuccess(resource);
                }

                @Override
                public void onLoadFailed(Drawable errorDrawable) {
                    super.onLoadFailed(errorDrawable);
                    loadCallBack.onLoadError();
                }
            });
        }

        if (actualClass == BitmapDrawable.class) {
            builder.into(new SimpleTarget<BitmapDrawable>() {
                @Override
                public void onResourceReady(BitmapDrawable resource, Transition<? super BitmapDrawable> transition) {
                    loadCallBack.onLoadSuccess(resource);
                }

                @Override
                public void onLoadFailed(Drawable errorDrawable) {
                    super.onLoadFailed(errorDrawable);
                    loadCallBack.onLoadError();
                }
            });
        }

        if (isBitmap && actualClass == Bitmap.class) {
            builder.into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                    loadCallBack.onLoadSuccess(resource);
                }

                @Override
                public void onLoadFailed(Drawable errorDrawable) {
                    super.onLoadFailed(errorDrawable);
                    loadCallBack.onLoadError();
                }
            });
        }
    }


}
