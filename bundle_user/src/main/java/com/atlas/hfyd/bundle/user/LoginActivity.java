package com.atlas.hfyd.bundle.user;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: hfyd
 * Date: 2018/12/24
 * Description:
 */
public class LoginActivity extends Activity {

    @BindView(R2.id.et_userName)
    EditText etUserName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R2.id.bt_login)
    public void onLoginClick(View view){
        Toast.makeText(this,"登录被点击",Toast.LENGTH_LONG).show();
    }
}
