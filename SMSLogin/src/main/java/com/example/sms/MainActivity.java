package com.example.sms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.ContactsPage;
import cn.smssdk.gui.RegisterPage;

/**
 * 集成SMS实现短信验证
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRe;
    private Button btnCon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        // 初始化启动
        SMSSDK.initSDK(this, "1bbf2a8407b73", "f8d58c9c3a1c8e4134f1bd25ff577924");

    }

    private void init() {
        btnRe = (Button) findViewById(R.id.btn_regedit);
        btnRe.setOnClickListener(this);
        btnCon = (Button) findViewById(R.id.btn_contact);
        btnCon.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_regedit:
                    //打开注册页面
                RegisterPage registerPage = new RegisterPage();
                registerPage.setRegisterCallback(new EventHandler() {
                    public void afterEvent(int event, int result, Object data) {
                        // 解析注册结果
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            @SuppressWarnings("unchecked")
                            HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
                            String country = (String) phoneMap.get("country");
                            String phone = (String) phoneMap.get("phone");

                        // 提交用户信息（此方法可以不调用）
//                            registerUser(country, phone);
                        }
                    }
                });
                registerPage.show(getApplicationContext());
                break;

            case R.id.btn_contact:
                //打开通信录好友列表页面
                ContactsPage contactsPage = new ContactsPage();
                contactsPage.show(getApplicationContext());
                break;
        }
    }
}
