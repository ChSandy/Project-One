package com.qf.projectone.fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qf.projectone.R;
import com.qf.projectone.bean.Bean;
import com.qf.projectone.bean.BeanInter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/11/21.
 */
public class MineFragament extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data=new ArrayList<>();
        init();

    }

    List<Bean> data;
    private void init() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BeanInter.BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BeanInter inter = retrofit.create(BeanInter.class);
        Call<Bean> call = inter.getVideoBean();
        call.enqueue(new Callback<Bean>() {
            @Override
            public void onResponse(Call<Bean> call, Response<Bean> response) {

                Bean body = response.body();
            }

            @Override
            public void onFailure(Call<Bean> call, Throwable t) {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mine_layout,container,false);
    }
}
