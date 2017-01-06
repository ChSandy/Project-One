package com.qf.projectone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23.
 */
public abstract class AbsAdapter<T> extends BaseAdapter {
    List<T> datas;
    LayoutInflater inflater;
   // int layoutId;
    int [] layoutId;
    public AbsAdapter(Context context, List<T> datas, int ... layoutId) {
        this.datas = datas;
        this.layoutId = layoutId;
        inflater = LayoutInflater.from(context);
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
    public int getViewTypeCount() {
        return layoutId.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int index=getItemViewType(position);
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(layoutId[index], parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //绑定数据
        bindData(position, holder);


        return convertView;
    }

    protected abstract void bindData(int position, ViewHolder holder);

    public static class ViewHolder {
        //保存的控件：是需要设置值的控件
        private View view;

        public ViewHolder(View view) {
            this.view = view;
        }


        //向子类提供一个方法，返回需要设置值的控件
        public View findViewById(int viewId) {
            //根据viewid，找到对应的控件
            return view.findViewById(viewId);
        }

    }
}
