package com.atlas.hfyd.bundle.coder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.taobao.atlas.remote.IRemote;
import android.taobao.atlas.remote.transactor.RemoteTransactor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hfyd.atlas.framework.natlv.event.bundle.RxComponentCaller;
import com.hfyd.atlas.framework.natlv.event.bundle.atlas.AtlasResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Author: hfyd
 * Date: 2018/12/17
 * Description:
 */
public class CoderFragment extends Fragment implements IRemote {

    @BindView(R2.id.tv_user_info)
    TextView tvUserInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_coder, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R2.id.bt_user_info)
    public void onGetUserInfoClick(View view) {
        RxComponentCaller creator = new RxComponentCaller(getActivity());
        UserComponentCaller caller = creator.create(UserComponentCaller.class);
        caller.getUserInfo("来自coder bundler的msg")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<AtlasResult<RemoteTransactor>>() {
                    @Override
                    public void call(AtlasResult<RemoteTransactor> result) {
                        String userInfo = result.getBundle().getString("userInfo");
                        tvUserInfo.setText(userInfo);

                        result.getiRemote().registerHostTransactor(new IRemote() {
                            @Override
                            public Bundle call(String s, Bundle bundle, IResponse iResponse) {
                                Toast.makeText(CoderFragment.this.getActivity()
                                        ,bundle.getString("testName"),Toast.LENGTH_LONG).show();
                                return null;
                            }

                            @Override
                            public <T> T getRemoteInterface(Class<T> aClass, Bundle bundle) {
                                return null;
                            }
                        });
                    }
                });
    }

    @Override
    public Bundle call(String s, Bundle bundle, IResponse iResponse) {
        Bundle response = new Bundle();
        response.putString("tabName", "掘金");
        return response;
    }

    @Override
    public <T> T getRemoteInterface(Class<T> aClass, Bundle bundle) {
        return null;
    }
}
