package com.qf.projectone.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.qf.projectone.R;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by Administrator on 2016/11/24.
 */
public class MyPullFrsh extends FrameLayout implements PtrUIHandler {

    //用来显示刷新的动画和图片
    ImageView imageView;

    //下拉开始的图片id
    int startId = R.drawable.refresh_001;
    //下拉结束时的图片id
    int endId = R.drawable.refresh_048;

    //刷新图片的数量
    int count = endId - startId;

    public MyPullFrsh(Context context) {
        super(context);
        init();
    }

    public MyPullFrsh(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    //初始化
    private void init() {
        imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        LayoutParams params = new LayoutParams(60, 60);
        params.setMargins(50, 50, 50, 50);
        params.gravity = Gravity.CENTER;
        addView(imageView, params);
        imageView.setImageResource(R.drawable.refresh_048);

    }


    @Override
    public void onUIReset(PtrFrameLayout frame) {
        //UI重置的调用
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        //UI更新准备调用
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        //UI开始刷新时调用
        imageView.setImageResource(R.drawable.icon_black_progressbar);
        RotateAnimation animation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(500);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        //开始动画
        imageView.setAnimation(animation);
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        //UI更新完成时调用
        //清除动画
        imageView.clearAnimation();
        imageView.setImageResource(R.drawable.refresh_048);
    }

    /**
     * @param frame
     * @param isUnderTouch 手指是否按下
     * @param status       状态
     * @param ptrIndicator UI变化指示器
     */
    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        //UI改变时调用

        if (status == PtrFrameLayout.PTR_STATUS_LOADING) {

            return;
        }

        int per = 0;
        if (ptrIndicator.getCurrentPercent() <= 1) {
            per = (int) (ptrIndicator.getCurrentPercent() * count);

        } else {
            per = count;
        }
        imageView.setImageResource(startId + per);

    }
}
