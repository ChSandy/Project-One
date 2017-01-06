package com.qf.projectone.bean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2016/12/15.
 */

public interface BeanInter {
    public static final String BASE="http://xb.huabao.me/";
    public static final String URL="?json=gender/category_article_list_hot_v2";

    @GET(URL)
    Call<Bean> getVideoBean();
}
