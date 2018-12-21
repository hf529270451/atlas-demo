package com.hfyd.atlas.framework.natlv.image;

import android.content.Context;

/**
 * Created by hfyd on 2018/3/7.
 * 图片加载公共接口
 * ImageLoader.with(this)
 *          .load(url)
 *          .asBitmap()
 *          .placeHolder(R.mipmap.ic_launcher)
 *          .scaleType(InitConfig.ScaleType_CircleCrop)
 *          .into(imageView);
 */
public class ImageLoader {

    public static LoaderManager with(Context context) {
        return new LoaderManager(context);
    }

    public static void trimMemory() {

    }

    public static void clearMemory() {

    }
}
