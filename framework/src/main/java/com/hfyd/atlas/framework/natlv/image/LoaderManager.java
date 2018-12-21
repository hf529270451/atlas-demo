package com.hfyd.atlas.framework.natlv.image;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.hfyd.atlas.framework.natlv.image.callback.LoadCallBack;
import com.hfyd.atlas.framework.natlv.image.config.InitConfig;
import com.hfyd.atlas.framework.natlv.image.config.IntoConfig;
import com.hfyd.atlas.framework.natlv.image.loader.GlideLoader;
import com.hfyd.atlas.framework.natlv.image.loader.ILoader;


/**
 * Created by hfyd on 2018/3/8.
 * LoaderManager
 */
public class LoaderManager {

    private InitConfig.InitConfigBuild initConfigBuild;

    public LoaderManager(Context context) {
        initConfigBuild= new InitConfig.InitConfigBuild();
        initConfigBuild.setContext(context);
    }

    public LoaderManager asBitmap(){
        initConfigBuild.asBitmap();
        return this;
    }

    public LoaderManager setDiskCachStranger(@InitConfig.CacheTypeSelfConstraint String cacheType){
        initConfigBuild.setCacheType(cacheType);
        return this;
    }

    public LoaderManager placeHolder(Drawable drawable){
        initConfigBuild.setPlaceHolderDrawable(drawable);
        return this;
    }

    public LoaderManager placeHolder(int resId){
        initConfigBuild.setPlaceHolderResId(resId);
        return this;
    }

    public LoaderManager scaleType(@InitConfig.ScaleTypeSelfConstraint String scaleType){
        initConfigBuild.setScaleType(scaleType);
        return this;
    }

    public LoaderManager load(Object url){
        initConfigBuild.setPath(url);
        return this;
    }

    public LoaderManager transform(@InitConfig.TransformTypeSelfConstraint String transformtype){
        initConfigBuild.transformType(transformtype);
        return this;
    }

    public void into(ImageView target){
        ILoader loader = new GlideLoader();
        loader.init(initConfigBuild.build());

        IntoConfig.IntoConfigBuild builder = new IntoConfig.IntoConfigBuild();
        builder.setTargetImageView(target);
        loader.into(builder.build());
    }

    public void into(LoadCallBack target){
        ILoader loader = new GlideLoader();
        loader.init(initConfigBuild.build());

        IntoConfig.IntoConfigBuild builder = new IntoConfig.IntoConfigBuild();
        builder.setLoadCallBack(target);
        loader.into(builder.build());
    }
}
