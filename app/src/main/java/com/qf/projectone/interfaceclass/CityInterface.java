package com.qf.projectone.interfaceclass;

import com.qf.projectone.bean.BannerBean;
import com.qf.projectone.utils.ApiManger;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/11/21.
 */
public interface CityInterface {
    @GET(ApiManger.CITY_CHOICE)
    Call<String> getCity();



}
