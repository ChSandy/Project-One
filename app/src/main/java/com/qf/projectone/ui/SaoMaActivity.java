package com.qf.projectone.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.qf.projectone.R;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

/**
 * Created by Administrator on 2016/11/24.
 */
public class SaoMaActivity extends BaseNoActionBarActivity implements CodeUtils.AnalyzeCallback {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.saomaactivity_layout);
        /**
         * 执行扫面Fragment的初始化操作
         */
        CaptureFragment captureFragment = new CaptureFragment();
        // 为二维码扫描界面设置定制化界面
        CodeUtils.setFragmentArgs(captureFragment, R.layout.saomafragment);

        captureFragment.setAnalyzeCallback(this);
        /**
         * 替换我们的扫描控件
         */
        getSupportFragmentManager().beginTransaction().replace(R.id.saom_fl, captureFragment).commit();
    }

    @Override
    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle.putString(CodeUtils.RESULT_STRING, result);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onAnalyzeFailed() {
        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle.putString(CodeUtils.RESULT_STRING, "扫码失败");
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void quxiao(View view) {
        finish();
    }
}
