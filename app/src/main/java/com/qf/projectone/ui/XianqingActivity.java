package com.qf.projectone.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.qf.projectone.R;
import com.qf.projectone.bean.XianQinBean;
import com.qf.projectone.interfaceclass.ShouYeInterFace;
import com.qf.projectone.utils.ApiManger;
import com.qf.projectone.widget.XianQingView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/11/25.
 */
public class XianqingActivity extends BaseNoActionBarActivity {

   XianQingView xianQingView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xianqing_activity);
        Intent intent=getIntent();
        String newid=intent.getStringExtra("id");

        xianQingView= (XianQingView) findViewById(R.id.xianqinview);

        getDatas(newid);


    }

    public void quxiao(View view){
        finish();
    }

    public void getDatas(String newsid) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiManger.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        ShouYeInterFace inter = retrofit.create(ShouYeInterFace.class);
        Call<XianQinBean> call = inter.getXianqingBean(newsid);

        call.enqueue(new Callback<XianQinBean>() {
            @Override
            public void onResponse(Call<XianQinBean> call, Response<XianQinBean> response) {
                XianQinBean bean = response.body();
                xianQingView.setData(bean);


            }

            @Override
            public void onFailure(Call<XianQinBean> call, Throwable t) {

            }
        });
    }
}
