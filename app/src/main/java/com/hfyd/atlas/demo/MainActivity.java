package com.hfyd.atlas.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.taobao.atlas.remote.RemoteFactory;
import android.taobao.atlas.remote.fragment.RemoteFragment;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.bt_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
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
        });

        switchToActivity("home",
                "atlas.fragment.intent.action.FIRST_FRAGMENT");

    }

    public void switchToActivity(String key,String activityName){
        RemoteFactory.requestRemote(RemoteFragment.class, this, new Intent(activityName),
                new RemoteFactory.OnRemoteStateListener<RemoteFragment>() {
                    @Override
                    public void onRemotePrepared(RemoteFragment iRemote) {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.content, iRemote)
                                .commit();
                    }

                    @Override
                    public void onFailed(String s) {
                        Log.e("UserRemoteActivity", s);
                    }
                });
    }
}
