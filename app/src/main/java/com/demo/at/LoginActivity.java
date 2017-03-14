package com.demo.at;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.demo.webview.IAct;
import com.demo.webview.R;
import com.demo.webview.view.activity.BaseActivity;

/**
 * 用途：
 * Created by milk on 17/3/10.
 * 邮箱：649444395@qq.com
 */

public class LoginActivity extends BaseActivity {
    private Button mBtnLogin;
    public static LoginListenter mLoginListenter;

    @Override
    protected int getRootId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginListenter.successUser("admin","1234");
                finish();
            }
        });
    }

    public static void getInstace(IAct iAct, LoginListenter loginListenter) {
        mLoginListenter = loginListenter;
        iAct.getActivity().startActivity(new Intent(iAct.getActivity(), LoginActivity.class));
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    public interface LoginListenter {
        void successUser(String phone, String pwd);

        void fail();
    }
}
