package com.qf.projectone.fragement;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.qf.projectone.R;

/**
 * Created by Administrator on 2016/11/21.
 */
public class FaxianFragament extends Fragment {

    WebView webView;
    ImageView img;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.faxian_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webView = (WebView) view.findViewById(R.id.webView);
        img = (ImageView) view.findViewById(R.id.img);


        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                //开始加载界面
                //隐藏webView,显示动画
                webView.setVisibility(View.GONE);
                img.setVisibility(View.VISIBLE);
                //取出Drawable
                AnimationDrawable anim = (AnimationDrawable) img.getDrawable();
                anim.start();

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //显示webView,隐藏动画
                webView.setVisibility(View.VISIBLE);
                img.setVisibility(View.GONE);
                AnimationDrawable anim = (AnimationDrawable) img.getDrawable();
                anim.stop();
            }

            /**
             * 当webview中的超链接(重定向)被点击时，回调该方法
             *
             * @param view
             * @param url
             * @return 返回值：表示这个点击事件是否继续给webview,true表示完全自己处理，false表示交给webview处理
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //启动一个Activity, 并传入Url
//                Intent intent=new Intent();
//                intent.putExtra(IntentUtils.URL,url);

                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("http://m.db.house.qq.com/index.php?mod=appkft&act=discover&cityid=4&rf=kanfang");

    }
}
