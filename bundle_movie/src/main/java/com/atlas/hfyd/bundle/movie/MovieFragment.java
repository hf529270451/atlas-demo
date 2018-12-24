package com.atlas.hfyd.bundle.movie;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.taobao.atlas.remote.IRemote;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author: hfyd
 * Date: 2018/12/17
 * Description:
 */
public class MovieFragment extends Fragment implements IRemote {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie, container, false);


        return rootView;
    }

    @Override
    public Bundle call(String s, Bundle bundle, IResponse iResponse) {
        Bundle response = new Bundle();
        response.putString("tabName", "豆瓣");
        return response;
    }

    @Override
    public <T> T getRemoteInterface(Class<T> aClass, Bundle bundle) {
        return null;
    }
}
