package com.hfyd.atlas.framework.natlv.event.bundle;

import rx.Subscriber;

/**
 * Author: hfyd
 * Date: 2018/12/19
 * Description:
 */
public interface RemoteHandler {

    void execute(Subscriber<? super Object> subscriber, MethodServiceInfo info) throws Exception;
}
