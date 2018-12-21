package com.hfyd.atlas.framework.natlv.event.bundle.atlas;

import android.os.Bundle;

/**
 * Author: hfyd
 * Date: 2018/12/19
 * Description:
 */
public class AtlasResult<T> {

    private Bundle bundle;

    private T iRemote;

    public AtlasResult(Bundle bundle, T iRemote) {
        this.bundle = bundle;
        this.iRemote = iRemote;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public T getiRemote() {
        return iRemote;
    }
}
