package com.qf.projectone.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.qf.projectone.R;

/**
 * Created by Administrator on 2016/11/24.
 */
public class ItemActivity extends BaseNoActionBarActivity {

    WebView webView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_activity);
        webView= (WebView) findViewById(R.id.item_activity);

        Intent  intent=getIntent();
        String urlid = intent.getStringExtra("id");
        String url="http://m.house.qq.com/a/"+urlid+"/";
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        Log.d("print", " "+url);
        webView.loadUrl(url);
    }
}
