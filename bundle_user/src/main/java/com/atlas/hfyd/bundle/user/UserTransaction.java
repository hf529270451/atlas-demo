package com.atlas.hfyd.bundle.user;

import android.os.Bundle;
import android.os.SystemClock;
import android.taobao.atlas.remote.HostTransactor;
import android.taobao.atlas.remote.IRemote;
import android.util.Log;

/**
 * Author: hfyd
 * Date: 2018/12/24
 * Description:
 */
public class UserTransaction implements IRemote {

    @Override
    public Bundle call(String s, final Bundle bundle, final IResponse iResponse) {
        switch (s) {
            case "getUserInfo":
                new Thread() {
                    @Override
                    public void run() {
                        //Log.e("UserTransaction", bundle.getString("testName"));
                        SystemClock.sleep(1000);
                        Bundle res = new Bundle();
                        res.putString("userInfo", "userInfo response successful");
                        iResponse.OnResponse(res);

                        HostTransactor remote = HostTransactor.get(UserTransaction.this);
                        remote.call("remoteCaller", bundle, null);
                    }
                }.start();
                break;
        }
        return null;
    }

    @Override
    public <T> T getRemoteInterface(Class<T> aClass, Bundle bundle) {
        return null;
    }

}
