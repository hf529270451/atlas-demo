package com.hfyd.atlas.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hfyd.atlas.spalsh.R;

/**
 * Author: hfyd
 * Date: 2018/12/19
 * Description:
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity_splash);

        View contentView = findViewById(R.id.cl_content);
        contentView.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClassName(getBaseContext(),"com.hfyd.atlas.demo.MainActivity");
                startActivity(intent);
                finish();
                overridePendingTransition(-1,android.R.anim.slide_out_right);
            }
        },3000);
    }
}
