package com.qf.projectone.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qf.projectone.R;
import com.qf.projectone.fragement.SplashFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/21.
 */
public class SplashActivity extends AppCompatActivity {

    ViewPager viewPager;
    List<Fragment> fragments;
    LinearLayout layout;

    boolean isCanMoveToRight = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        init();

    }

    public void btnclick(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }


    private void init() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        layout = (LinearLayout) findViewById(R.id.dot_lalyout);


        fragments = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            SplashFragment sf = new SplashFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(SplashFragment.INDEX_KEY, i);
            Log.d("print", " i====" + i);
            sf.setArguments(bundle);
            fragments.add(sf);
        }

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        Log.d("print", " size" + fragments.size());
        viewPager.setAdapter(adapter);

        //小白点
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
        params.setMargins(5, 5, 5, 5);

        for (int i = 0; i < fragments.size(); i++) {
            ImageView imageView = new ImageView(this);
            if (i == 0) {
                imageView.setImageResource(R.drawable.sele_dot);
            } else {
                imageView.setImageResource(R.drawable.no_dot);
            }
            layout.addView(imageView, params);

        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }


            @Override
            public void onPageSelected(int position) {

                //设置小白点滑动
                int size = layout.getChildCount();
                for (int i = 0; i < size; i++) {
                    ImageView iv = (ImageView) layout.getChildAt(i);
                    if (i == position) {
                        iv.setImageResource(R.drawable.sele_dot);
                    } else {
                        iv.setImageResource(R.drawable.no_dot);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //根据状态判断
                //如果是最后一页，并向右滑动，进入界面
                switch (state) {
                    /**
                     *滚动状态
                     */
                    case ViewPager.SCROLL_STATE_DRAGGING: {
                        Log.d("print", "SCROLL_STATE_DRAGGING ------------>滚动状态");
                        isCanMoveToRight = false;
                    }
                    break;
                    /**
                     *停止状态
                     */
                    case ViewPager.SCROLL_STATE_IDLE: {
                        Log.d("print", "SCROLL_STATE_IDLE----->停止状态 ");
                        if(!isCanMoveToRight&&viewPager.getCurrentItem()==(viewPager.getAdapter().getCount()-1)){
                            Intent intent = new Intent(SplashActivity.this, CityActivity.class);
                            startActivity(intent);

                            finish();
                        }
                        isCanMoveToRight = false;

                    }
                    break;
                    /**
                     * 惯性状态
                     */
                    case ViewPager.SCROLL_STATE_SETTLING: {
                        Log.d("print", "SCROLL_STATE_SETTLING---->惯性状态 ");
                        isCanMoveToRight = true;
                    }
                    break;
                }
            }
        });


        //动画效果
        viewPager.setPageTransformer(true, new MyTran());

    }

    int[] imagId = {R.id.iv1, R.id.iv2, R.id.iv3};

    class MyTran implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View page, float position) {
            //把page-->ViewGrop
            //遍历page，拿到三个做动画的ImageView
            //根据position设置View的属性
            float transx = page.getWidth() * position;
            for (int i = 0; i < imagId.length; i++) {
                View view = page.findViewById(imagId[i]);
                if (view != null) {
                    view.setTranslationX(transx);
                }
                //下一个控件的x偏移量
                transx *= 10f;
            }
        }
    }
}
