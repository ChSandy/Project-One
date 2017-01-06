package com.qf.projectone.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/15.
 */

public class Bean {

    /**
     * CategoryId : 30
     * Pic : null
     * Content : 正要开始游戏，听见那熟悉的声音：敌军还有30秒到达战场，碾碎他们！这个时候女友在旁边取笑他说自从他迷上LOL后，身体差了很多。他不乐意了，放下鼠标直接把她推倒，狠狠的干了一发！正当他提上裤子的同时听见“全军出击！”，速战速决，还好，没耽误这局。。
     * UserName : EC5DFFCCC9C7AFD2ED3CBD7CD4551134
     * UserNick : 玉吴
     * UserIcon : http://img.xb.huabao.me/wp-content/uploads/uicons/e8/de/3ad707fe3e35658a402bb745f10cdee8.jpg
     * UserLevel : 199247
     * UserId : 1750247
     * LonX : 0
     * LatY : 0
     * Location : null
     * Distance : null
     * IsVip : 0
     * VipPoint : 0
     * CommentStyle :
     * WapUrl : null
     * PicCount : 0
     * Subject :
     * ArticleId : 10623102
     * Title : 正要开始游戏，听见那熟悉的声音：敌军还有30秒到达战场，碾碎他们
     * Pubtime : 1481775308000
     * Goods : 2831
     * Shares : 756
     * Comments : 34
     * Hits : 5578
     * Favorites : 407
     */

    private int CategoryId;
    private Object Pic;
    private String Content;
    private String UserName;
    private String UserNick;
    private String UserIcon;
    private int UserLevel;
    private int UserId;
    private int LonX;
    private int LatY;
    private Object Location;
    private Object Distance;
    private int IsVip;
    private int VipPoint;
    private String CommentStyle;
    private Object WapUrl;
    private int PicCount;
    private String Subject;
    private int ArticleId;
    private String Title;
    private long Pubtime;
    private int Goods;
    private int Shares;
    private int Comments;
    private int Hits;
    private int Favorites;

    public static List<Bean> arrayBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<Bean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<Bean> arrayBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<Bean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int CategoryId) {
        this.CategoryId = CategoryId;
    }

    public Object getPic() {
        return Pic;
    }

    public void setPic(Object Pic) {
        this.Pic = Pic;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getUserNick() {
        return UserNick;
    }

    public void setUserNick(String UserNick) {
        this.UserNick = UserNick;
    }

    public String getUserIcon() {
        return UserIcon;
    }

    public void setUserIcon(String UserIcon) {
        this.UserIcon = UserIcon;
    }

    public int getUserLevel() {
        return UserLevel;
    }

    public void setUserLevel(int UserLevel) {
        this.UserLevel = UserLevel;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public int getLonX() {
        return LonX;
    }

    public void setLonX(int LonX) {
        this.LonX = LonX;
    }

    public int getLatY() {
        return LatY;
    }

    public void setLatY(int LatY) {
        this.LatY = LatY;
    }

    public Object getLocation() {
        return Location;
    }

    public void setLocation(Object Location) {
        this.Location = Location;
    }

    public Object getDistance() {
        return Distance;
    }

    public void setDistance(Object Distance) {
        this.Distance = Distance;
    }

    public int getIsVip() {
        return IsVip;
    }

    public void setIsVip(int IsVip) {
        this.IsVip = IsVip;
    }

    public int getVipPoint() {
        return VipPoint;
    }

    public void setVipPoint(int VipPoint) {
        this.VipPoint = VipPoint;
    }

    public String getCommentStyle() {
        return CommentStyle;
    }

    public void setCommentStyle(String CommentStyle) {
        this.CommentStyle = CommentStyle;
    }

    public Object getWapUrl() {
        return WapUrl;
    }

    public void setWapUrl(Object WapUrl) {
        this.WapUrl = WapUrl;
    }

    public int getPicCount() {
        return PicCount;
    }

    public void setPicCount(int PicCount) {
        this.PicCount = PicCount;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }

    public int getArticleId() {
        return ArticleId;
    }

    public void setArticleId(int ArticleId) {
        this.ArticleId = ArticleId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public long getPubtime() {
        return Pubtime;
    }

    public void setPubtime(long Pubtime) {
        this.Pubtime = Pubtime;
    }

    public int getGoods() {
        return Goods;
    }

    public void setGoods(int Goods) {
        this.Goods = Goods;
    }

    public int getShares() {
        return Shares;
    }

    public void setShares(int Shares) {
        this.Shares = Shares;
    }

    public int getComments() {
        return Comments;
    }

    public void setComments(int Comments) {
        this.Comments = Comments;
    }

    public int getHits() {
        return Hits;
    }

    public void setHits(int Hits) {
        this.Hits = Hits;
    }

    public int getFavorites() {
        return Favorites;
    }

    public void setFavorites(int Favorites) {
        this.Favorites = Favorites;
    }
}
