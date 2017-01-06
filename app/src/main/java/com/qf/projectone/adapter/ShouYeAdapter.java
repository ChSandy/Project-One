package com.qf.projectone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.qf.projectone.bean.ShouYeBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23.
 */
public class ShouYeAdapter extends BaseAdapter {

    List<ShouYeBean.DataBean> datas;
    LayoutInflater inflater;

    public ShouYeAdapter(Context context,List<ShouYeBean.DataBean> datas) {
        this.datas = datas;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }


    class ViewHolder{

    }
}
