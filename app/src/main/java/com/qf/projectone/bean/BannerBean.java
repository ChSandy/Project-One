package com.qf.projectone.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23.
 */
public class BannerBean {

    /**
     * type : 3
     * picurl : http://p.qpic.cn/estate/0/512c9096291a2baf1b160cf72f08be34.jpg/0
     * title : 50-70㎡智慧商务空间
     * houseid : 177446
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String type;
        private String picurl;
        private String title;
        private String houseid;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getHouseid() {
            return houseid;
        }

        public void setHouseid(String houseid) {
            this.houseid = houseid;
        }
    }
}
