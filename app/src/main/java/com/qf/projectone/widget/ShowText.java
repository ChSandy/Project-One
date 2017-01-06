package com.qf.projectone.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/11/22.
 */
public class ShowText extends View {

    Paint paint;

    String text;

    float textW;

    float textH;

    RectF rf;

    public ShowText(Context context) {
        super(context);
        init();
    }

    public ShowText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#12A6C3"));
        paint.setTextSize(200);
        textW = paint.measureText("M");
        textH = paint.descent() - paint.ascent();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //  super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasureWH(widthMeasureSpec, 1), getMeasureWH(heightMeasureSpec, 2));

        //设置矩形区域
        rf = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());


    }

    private int getMeasureWH(int wh, int type) {
        int mode = MeasureSpec.getMode(wh);
        int size = MeasureSpec.getSize(wh);
        //根据模式，计算大小
        switch (mode) {
            case MeasureSpec.EXACTLY:
            case MeasureSpec.UNSPECIFIED:
                return size;
            case MeasureSpec.AT_MOST: {
                //wrap_content
                //如果是宽度，则是文本的测量宽度
                if (type == 1) {
                    //测量宽度
                    return (int) textW + getPaddingLeft() + getPaddingRight();
                } else {
                    //如果 是高度 ，则是所有文本的高度之和
                    return (int) textH + getPaddingTop() + getPaddingBottom();
                }
            }
        }
        return size;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制背景
        paint.setColor(Color.parseColor("#12A6C3"));
        canvas.drawRoundRect(rf, 20, 20, paint);
        //会之后文本
        //设置文本颜色

        paint.setColor(Color.parseColor("#ffffff"));
        if (text != null) {
            float dx = getMeasuredWidth() -paint.measureText(text);
            float dy = (getMeasuredHeight() - textH) / 2;
            canvas.drawText(text, dx / 2, dy - paint.ascent(), paint);
        }
    }

    public void setTextView(String string) {
        text = string;
        invalidate();
    }


}
