package com.hfyd.atlas.demo.bean;

import android.taobao.atlas.remote.fragment.RemoteFragment;

import com.hfyd.atlas.framework.natlv.event.bundle.atlas.AtlasResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: hfyd
 * Date: 2018/12/24
 * Description:
 */
public class MainTabZipBean {

    private AtlasResult<RemoteFragment> news;
    private AtlasResult<RemoteFragment> coder;
    private AtlasResult<RemoteFragment> movie;
    private AtlasResult<RemoteFragment> user;
    private List<AtlasResult<RemoteFragment>> contentList = new ArrayList<>();

    public MainTabZipBean(AtlasResult<RemoteFragment> news, AtlasResult<RemoteFragment> coder
            , AtlasResult<RemoteFragment> movie, AtlasResult<RemoteFragment> user) {
        this.news = news;
        this.coder = coder;
        this.movie = movie;
        this.user = user;
        contentList.add(news);
        contentList.add(coder);
        contentList.add(movie);
        contentList.add(user);
    }

    public List<AtlasResult<RemoteFragment>> getTabContentList(){
        return contentList;
    }
}
