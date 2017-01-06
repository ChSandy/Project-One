package com.qf.projectone.utils;

/**
 * Created by Administrator on 2016/11/21.
 */
public class ApiManger {

    //主机域名
    public static final String BASE_URL = "http://ikft.house.qq.com/";

    //城市选择
    public static final String CITY_CHOICE = "index.php?guid=866500021200250&devua=appkft_1080_1920_XiaomiMI4LTE_1.8.3_Android19&act=kftcitylistnew&channel=71&devid=866500021200250&appname=QQHouse&mod=appkft";

    /**
     * 首页 ListView内容
     * <p/>
     * 1)进入时：reqnum=10,pageflag=0,buttonmore=0;
     * 2)点击查看更多时：reqnum=20,pageflag=0,buttonmore=1;
     * 3)刷新时：reqnum=20,pageflag=1,buttonmore=1,cityid;
     */
    public static final String FIRST_PAGE_LISTVIEW = "index.php?guid=866500021200250&devua=appkft_1080_1920_XiaomiMI4LTE_1.8.3_Android19&devid=866500021200250&appname=QQHouse&mod=appkft&reqnum=%d&act=newslist&channel=71";


    /**
     * 首页广告内容
     * &cityid=%s
     */
    public static final String FIRST_PAGE_WEBVIEW = "http://ikft.house.qq.com/index.php?guid=866500021200250&devua=appkft_1080_1920_XiaomiMI4LTE_1.8.3_Android19&devid=866500021200250&appname=QQHouse&mod=appkft&act=homepage&channel=71";


    /**
     * 资讯详情
     * &newsid=%s
     */
    public static final String NEWS_DETAIL = "index.php?guid=866500021200250&devua=appkft_1080_1920_XiaomiMI4LTE_1.8.3_Android19&devid=866500021200250&appname=QQHouse&mod=appkft&act=newsdetail&channel=71";
 /*   1)进入时：reqnum=10,pageflag=0,buttonmore=0;
    * 2)点击查看更多时：reqnum=20,pageflag=0,buttonmore=1;
    * 3)刷新时：reqnum=20,pageflag=1,buttonmore=1;
    */
    public static final String ADDMORE = "http://ikft.house.qq.com/index.php?guid=866500021200250&devua=appkft_1080_1920_XiaomiMI4LTE_1.8.3_Android19&devid=866500021200250&appname=QQHouse&mod=appkft&reqnum=20&act=newslist&channel=71&buttonmore=1&cityid=%s";


}

