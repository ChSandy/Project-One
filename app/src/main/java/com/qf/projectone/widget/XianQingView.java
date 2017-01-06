package com.qf.projectone.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qf.projectone.R;
import com.qf.projectone.bean.XianQinBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/25.
 */
public class XianQingView extends LinearLayout {

    public XianQingView(Context context) {
        super(context);
        init();
    }

    public XianQingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
    }

    public void setData(XianQinBean bean) {
        if (bean == null) {
            return;
        }

        LayoutParams params = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(5, 20, 5, 20);
        //------------
        TextView textView = new TextView(getContext());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.text_title_size));
        textView.setText(bean.getTitle());
        addView(textView, params);
        //------------
        TextView timeText = new TextView(getContext());
        timeText.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.text_time_size));
        timeText.setText(bean.getSource() + "  " + bean.getTime());
        addView(timeText, params);
        //-------------
        View view = new View(getContext());
        LinearLayout.LayoutParams params1 = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 3);
        view.setBackgroundColor(Color.parseColor("#FF333333"));
        addView(view);

        //========content============================
        List<XianQinBean.ContentBean> entities = bean.getContent();
        for (XianQinBean.ContentBean entity : entities) {
            int type = entity.getType();
            String content=entity.getValue();
            switch (type) {
                case 1: {
                    TextView tv=new TextView(getContext());
                    tv.setText(content);
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen.text_content_size));
                    addView(tv,params);
                }
                break;
                case 2: {
                    ImageView imageView=new ImageView(getContext());
                    Glide.with(getContext()).load(content).into(imageView);
                    addView(imageView,params);
                }
                break;
            }
        }
    }

}
