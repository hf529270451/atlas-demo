package com.hfyd.atlas.core.base;

import android.app.Application;

/**
 * Created by 52927 on 2018/3/9.
 * ScreenUtils
 */
public class ScreenUtils {

    public static void activateScreenAdapt(Application context){
        new ScreenAdaptHelper(context, 375).activate();
    }

    public static float pt2px(Application application,float px){
        return ScreenAdaptHelper.pt2px(application,px);
    }
}
