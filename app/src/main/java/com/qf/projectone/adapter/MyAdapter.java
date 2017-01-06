package com.qf.projectone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qf.projectone.R;
import com.qf.projectone.bean.CityBean;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by Administrator on 2016/11/22.
 */
public class MyAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    List<CityBean> data;
    LayoutInflater inflater;
    List<CityBean> allData;

    public MyAdapter(List<CityBean> data, Context context) {
        this.data = data;
        inflater = LayoutInflater.from(context);
        allData = new ArrayList<>();
        allData.addAll(data);
    }

    public void setAllData(List<CityBean> data1) {
        allData.clear();
        allData.addAll(data1);
    }

    //=======================
    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.cityitem_head, parent, false);
            viewHolder = new HeaderViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (HeaderViewHolder) convertView.getTag();
        }
        viewHolder.tv_head.setText(data.get(position).getLetter());
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        return data.get(position).getTypeId();
    }

    class HeaderViewHolder {
        TextView tv_head;

        public HeaderViewHolder(View view) {

            tv_head = (TextView) view.findViewById(R.id.tv_cityitem_head);
        }
    }

    //==========================

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CityViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.cityitem, parent, false);
            viewHolder = new CityViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (CityViewHolder) convertView.getTag();
        }
        viewHolder.tv_cityName.setText(data.get(position).getCityname());
        return convertView;
    }

    class CityViewHolder {
        TextView tv_cityName;

        public CityViewHolder(View view) {
            tv_cityName = (TextView) view.findViewById(R.id.tv_cityitem);
        }
    }

    public void serchCity(String value) {
        data.clear();
        if (value == null || value.length() == 0) {
            data.addAll(allData);
        } else {
            for (CityBean city : allData) {
                if (city.getCityname().contains(value)) {
                    data.add(city);
                } else if (city.getCitypinyin().contains(value)) {
                    data.add(city);
                }

            }
            notifyDataSetChanged();
        }


    }
}
