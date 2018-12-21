package com.hfyd.atlas.framework.natlv.event.bundle;

import android.content.Context;

/**
 * Created by hfyd on 2018/8/30.
 */

public class ComponentInfo {

    private String callId;
    private Context context;

    public ComponentInfo(String callId, Context context) {
        this.callId = callId;
        this.context = context;
    }

    public String getCallId() {
        return callId;
    }

    public Context getContext() {
        return context;
    }
}
