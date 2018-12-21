package com.hfyd.atlas.framework.natlv.logger;

import android.util.Log;

import com.google.gson.Gson;
import com.hfyd.atlas.framework.natlv.parse.JsonUtils;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Settings;

/**
 * Author: hfyd
 * Date: 2018/12/19
 * Description:
 */
public class HLog {

    private static boolean enable = true;
    private static String NORMAL_TAG = "AtlasDemo";

    public static void init(boolean enable) {
        HLog.enable = enable;
        normalLoggerSetting();
    }

    private static void normalLoggerSetting() {
        LogLevel logLevel = enable ? LogLevel.FULL : LogLevel.NONE;
        Logger.init(NORMAL_TAG).logLevel(logLevel);
    }

    public static void error(String msg) {
        Logger.e(msg);
    }

    public static void error(Throwable e) {
        error(Log.getStackTraceString(e));
    }

    public static void warm(String msg) {
        Logger.w(msg);
    }

    public static void json(String json) {
        Logger.json(json);
    }

    public static void jsonObj(Object jsonObj) {
        json(JsonUtils.toJson(jsonObj));
    }
}
