package com.hfyd.atlas.framework.natlv.event.middleware;

import android.os.Bundle;

/**
 * Author: hfyd
 * Date: 2018/12/24
 * Description:
 */
public interface IEventReceiver {

    void onEventReceiver(String command, Bundle bundle);
}
