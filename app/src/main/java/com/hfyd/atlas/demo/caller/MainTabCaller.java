package com.hfyd.atlas.demo.caller;

import android.os.Bundle;
import android.taobao.atlas.remote.fragment.RemoteFragment;

import com.hfyd.atlas.framework.natlv.event.bundle.Async;
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
public interface MainTabCaller {

    @Sync(action = "atlas.fragment.intent.action.news.NEWS_FRAGMENT")
    @TransactionLevel(TransactionType.TYPE_FRAGMENT)
    Observable<AtlasResult<RemoteFragment>> getNewsFragment();

    @Sync(action = "atlas.fragment.intent.action.coder.CODER_FRAGMENT")
    @TransactionLevel(TransactionType.TYPE_FRAGMENT)
    Observable<AtlasResult<RemoteFragment>> getCoderFragment();

    @Sync(action = "atlas.fragment.intent.action.movie.MOVIE_FRAGMENT")
    @TransactionLevel(TransactionType.TYPE_FRAGMENT)
    Observable<AtlasResult<RemoteFragment>> getMovieFragment();

    @Sync(action = "atlas.fragment.intent.action.user.USER_FRAGMENT")
    @TransactionLevel(TransactionType.TYPE_FRAGMENT)
    Observable<AtlasResult<RemoteFragment>> getUserFragment();
}
