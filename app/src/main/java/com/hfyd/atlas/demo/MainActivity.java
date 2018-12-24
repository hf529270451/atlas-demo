package com.hfyd.atlas.demo;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.taobao.atlas.remote.RemoteFactory;
import android.taobao.atlas.remote.fragment.RemoteFragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hfyd.atlas.demo.bean.MainTabZipBean;
import com.hfyd.atlas.demo.caller.MainTabCaller;
import com.hfyd.atlas.demo.widget.adapter.MyPagerAdapter;
import com.hfyd.atlas.framework.natlv.event.bundle.RxComponentCaller;
import com.hfyd.atlas.framework.natlv.event.bundle.atlas.AtlasResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func4;

public class MainActivity extends FragmentActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.tb_main_nav)
    TabLayout tbMainNav;
    @BindView(R.id.vp_main_content)
    ViewPager vpMainContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        injectTabAndContent();

        /*findViewById(R.id.bt_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        Updater.update(getBaseContext());
                    }
                }.start();
            }
        });

        findViewById(R.id.bt_dymic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName(MainActivity.this,
                        "com.hfyd.atlas.demo.active.ActiveActivity");
                startActivity(intent);
            }
        });*/

    }

    private void injectTabAndContent() {
        RxComponentCaller creator = new RxComponentCaller(this);
        MainTabCaller caller = creator.create(MainTabCaller.class);


        Observable.zip(caller.getNewsFragment(), caller.getCoderFragment(), caller.getMovieFragment(), caller.getUserFragment(),
                new Func4<AtlasResult<RemoteFragment>, AtlasResult<RemoteFragment>, AtlasResult<RemoteFragment>
                        , AtlasResult<RemoteFragment>, MainTabZipBean>() {
                    @Override
                    public MainTabZipBean call(AtlasResult<RemoteFragment> news
                            , AtlasResult<RemoteFragment> coder
                            , AtlasResult<RemoteFragment> movie
                            , AtlasResult<RemoteFragment> user) {
                        return new MainTabZipBean(news, coder, movie, user);
                    }
                })
                .subscribe(new Action1<MainTabZipBean>() {
                    @Override
                    public void call(MainTabZipBean zipBean) {
                        List<AtlasResult<RemoteFragment>> tabContentList = zipBean.getTabContentList();
                        if (tabContentList != null && tabContentList.size() > 0) {
                            showTabAndContent(tabContentList);
                        }
                    }

                });
    }


    private void showTabAndContent(List<AtlasResult<RemoteFragment>> tabContentList) {
        List<String> titles = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<>();

        for (AtlasResult<RemoteFragment> tabItem : tabContentList) {
            fragments.add(tabItem.getiRemote());
            titles.add(tabItem.getBundle().getString("tabName"));
        }

        for (int i = 0; i < titles.size(); i++) {
            tbMainNav.addTab(tbMainNav.newTab());
        }
        for (int i = 0; i < titles.size(); i++) {
            tbMainNav.getTabAt(i).setText(titles.get(i));
        }

        vpMainContent.setAdapter(new MyPagerAdapter(getSupportFragmentManager()
                , MainActivity.this, fragments, titles));
        tbMainNav.setupWithViewPager(vpMainContent);
    }
}
