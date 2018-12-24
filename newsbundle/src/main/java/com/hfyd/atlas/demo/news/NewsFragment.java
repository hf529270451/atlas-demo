package com.hfyd.atlas.demo.news;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.taobao.atlas.remote.IRemote;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: hfyd
 * Date: 2018/12/17
 * Description:
 */
public class NewsFragment extends Fragment implements IRemote {

    @BindView(R2.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R2.id.rv_content)
    RecyclerView rvContent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);
        ButterKnife.bind(this, rootView);
        initNews();
        return rootView;
    }

    private void initNews() {
        refresh.setEnableRefresh(true);
        refresh.setRefreshHeader(new ClassicsHeader(getActivity()));
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        SystemClock.sleep(2000);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                refresh.finishRefresh();
                            }
                        });
                    }
                }.start();
            }
        });

        rvContent.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvContent.setAdapter(new NewsAdapter());
    }

    @Override
    public Bundle call(String s, Bundle bundle, IResponse iResponse) {
        Bundle response = new Bundle();
        response.putString("tabName", "新闻");
        return response;
    }

    @Override
    public <T> T getRemoteInterface(Class<T> aClass, Bundle bundle) {
        return null;
    }
}
