package com.qf.projectone.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.qf.projectone.R;
import com.qf.projectone.utils.SharedUtils;

public class WelcomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //延时2s跳转
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //判断是否是第一次启动，如果是第一次，跳转到引导界面
                if (SharedUtils.isFirstRun(WelcomActivity.this)) {
                    Intent intent = new Intent(WelcomActivity.this, SplashActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    //如果不是第一次启动，跳转到主界面
                    Intent intent = new Intent(WelcomActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }


              /*  //测试，跳转到引导界面
                Intent intent = new Intent(WelcomActivity.this, SplashActivity.class);
                startActivity(intent);
                finish();*/
            }
        }, 2000);
    }
}
