package com.qf.projectone.interfaceclass;

import com.qf.projectone.bean.BannerBean;
import com.qf.projectone.bean.XianQinBean;
import com.qf.projectone.utils.ApiManger;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/11/23.
 */
public interface ShouYeInterFace {

    @GET(ApiManger.FIRST_PAGE_LISTVIEW)
    Call<String> getListViewContent(@Query("pageflag") String pageflag, @Query("buttonmore") String buttonmore, @Query("cityid") String cityid);

    @GET(ApiManger.FIRST_PAGE_WEBVIEW)
    Call<BannerBean> getbannerBean(@Query("cityid") String cityid);

    @GET(ApiManger.NEWS_DETAIL)
    Call<XianQinBean> getXianqingBean(@Query("newsid") String newsid);

    //分页
    @GET(ApiManger.ADDMORE)
    Call<BannerBean> getbannerBeanMore(@Query("pageflag") String pageflag,@Query("cityid")String cityid);

}
