package com.qf.projectone.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.qf.projectone.R;
import com.qf.projectone.bean.BannerBean;
import com.qf.projectone.interfaceclass.ShouYeInterFace;
import com.qf.projectone.utils.ApiManger;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/11/23.
 */
public class BannerView extends FrameLayout {

    Banner banner;

    public BannerView(Context context) {
        super(context);
        init();
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.banner_layout, this, true);
        banner = (Banner) findViewById(R.id.banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        //     banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        //     banner.setBannerTitles(Arrays.asList(titles));
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用

    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             常用的图片加载库：
             Universal Image Loader：一个强大的图片加载库，包含各种各样的配置，最老牌，使用也最广泛。
             Picasso: Square出品，必属精品。和OkHttp搭配起来更配呦！
             Volley ImageLoader：Google官方出品，可惜不能加载本地图片~
             Fresco：Facebook出的，天生骄傲！不是一般的强大。
             Glide：Google推荐的图片加载库，专注于流畅的滚动。
             */

            //Glide 加载图片简单用法
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(context).load(path).into(imageView);
        }
    }

    public void setCityId(String id){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(ApiManger.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        ShouYeInterFace bannerInter = retrofit.create(ShouYeInterFace.class);
        Call<BannerBean> call = bannerInter.getbannerBean(id);
        call.enqueue(new Callback<BannerBean>() {
            @Override
            public void onResponse(Call<BannerBean> call, Response<BannerBean> response) {
                BannerBean bean=response.body();
                ArrayList<String> title=new ArrayList<>();
                ArrayList<String> urls=new ArrayList<>();
                List<BannerBean.DataBean> entity=bean.getData();

                for(BannerBean.DataBean city:entity){
                    title.add(city.getTitle());
                    urls.add(city.getPicurl());
                }

                //设置图片集合
                try {
                    banner.setImages(urls);
                    Log.d("print", " urls.size"+urls.size() );
                    banner.setBannerTitles(title);
                    Log.d("print", " title.size"+title.size());
                    banner.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<BannerBean> call, Throwable t) {

            }
        });
    }
}
