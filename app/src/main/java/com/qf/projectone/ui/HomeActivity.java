package com.qf.projectone.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.qf.projectone.R;
import com.qf.projectone.fragement.FaxianFragament;
import com.qf.projectone.fragement.MineFragament;
import com.qf.projectone.fragement.MsgFragament;
import com.qf.projectone.fragement.ShouyeFragament;
import com.qf.projectone.utils.SharedUtils;

/**
 * Created by Administrator on 2016/11/21.
 */
public class HomeActivity extends BaseNoActionBarActivity {

    //底部导航文字数组
    private String[] titles = {"首页", "发现", "消息", "我的"};
    //底部导航图片id
    private int[] images = {R.drawable.home, R.drawable.find, R.drawable.msg, R.drawable.mine};
    //TabHost使用的Fragment的class
    Class[] fragments =
            {
                    ShouyeFragament.class,
                    FaxianFragament.class,
                    MsgFragament.class,
                    MineFragament.class
            };

    //控件声明
    private FragmentTabHost tabHost;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        SharedUtils.saveFirstRun(this);
        init();
    }

    private void init() {
        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);

        inflater = LayoutInflater.from(this);

        tabHost.setup(this, getSupportFragmentManager(), R.id.fl_home);
        for (int i = 0; i < titles.length; i++) {
            TabHost.TabSpec tabItem = tabHost.newTabSpec(i + "");

            tabItem.setIndicator(getTabView(i));

            tabHost.addTab(tabItem, fragments[i], null);
        }

    }

    LayoutInflater inflater;

    private View getTabView(int dex) {
        View view = inflater.inflate(R.layout.tab_item_layout, null);
        ImageView iv = (ImageView) view.findViewById(R.id.iv_home);
        iv.setImageResource(images[dex]);
        TextView tv = (TextView) view.findViewById(R.id.tv_home);
        tv.setText(titles[dex]);
        return view;
    }


    //双击返回键退出
    // 定义一个boolean来判断是否退出
    static boolean isExit = false;

    // 按键监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // 用handler来接收更改状态信息
    private static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 若在2秒后用户未再次点击返回键，则将isExit置为false
            isExit = false;
        }
    };

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次后退键退出程序", Toast.LENGTH_SHORT).show();
            // 利用handler延迟2秒发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            Log.d("EXIT", "exit application");
            // 结束
            this.finish();
        }
    }
}
