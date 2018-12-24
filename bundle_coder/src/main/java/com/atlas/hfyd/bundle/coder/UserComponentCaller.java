package com.atlas.hfyd.bundle.coder;

import android.taobao.atlas.remote.transactor.RemoteTransactor;

import com.hfyd.atlas.framework.natlv.event.bundle.Async;
import com.hfyd.atlas.framework.natlv.event.bundle.Param;
import com.hfyd.atlas.framework.natlv.event.bundle.Sync;
import com.hfyd.atlas.framework.natlv.event.bundle.TransactionLevel;
import com.hfyd.atlas.framework.natlv.event.bundle.TransactionType;
import com.hfyd.atlas.framework.natlv.event.bundle.atlas.AtlasResult;

import rx.Observable;

/**
 * Author: hfyd
 * Date: 2018/12/24
 * Description:
 */
public interface UserComponentCaller {


    @Async(action = "atlas.transaction.intent.action.user.TRANSACTION",
            command = "getUserInfo")
    @TransactionLevel(TransactionType.TYPE_TRANSACTION)
    Observable<AtlasResult<RemoteTransactor>> getUserInfo(@Param("testName") String testName);
}
