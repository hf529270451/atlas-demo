package com.hfyd.atlas.demo.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.taobao.atlas.remote.IRemote;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Author: hfyd
 * Date: 2018/12/17
 * Description:
 */
public class NewsFragment extends Fragment implements IRemote {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getActivity(),"firstBundle haschanged 1.0.0",Toast.LENGTH_LONG).show();
    }
//    @Override
//    public void onListFragmentInteraction(DummyContent.DummyItem item) {
//
//    }

    @Override
    public Bundle call(String s, Bundle bundle, IResponse iResponse) {
        return null;
    }

    @Override
    public <T> T getRemoteInterface(Class<T> aClass, Bundle bundle) {
        return null;
    }
}
