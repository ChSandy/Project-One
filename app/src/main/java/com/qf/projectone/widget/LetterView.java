package com.qf.projectone.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/11/22.
 */
public class LetterView extends View {

    public static final String[] lettes = {
            "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X",
            "Y", "Z"};

    Paint paint;

    //文字的宽
    float textW;
    //文字的高
    float textH;

    int postion = -1;

    public interface CallBack{
        public void setText(int postion,String str);
        public void setView();
    }

    CallBack callBack;
    public void setOnLinsten(CallBack lister){
        this.callBack=lister;
    }


    public LetterView(Context context) {
        super(context);
        init();
    }


    public LetterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(25);
        paint.setColor(Color.parseColor("#12A6C3"));
        //计算文本的高度
        textH = paint.descent() - paint.ascent();
        //测量第一个字母的宽度
        textW = paint.measureText(lettes[0]);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasureWH(widthMeasureSpec, 1), getMeasureWH(heightMeasureSpec, 2));
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
                    return (int) (lettes.length * textH) + getPaddingTop() + getPaddingBottom();
                }
            }
        }
        return size;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < lettes.length; i++) {
            //从字母数组中，取出每个字母，并绘制出来
            float dx = getMeasuredWidth() - paint.measureText(lettes[i]);
            float dy = i * textH - paint.ascent() + getPaddingTop();
            //选中字母变色
            if (i == postion) {
                paint.setFakeBoldText(true);
                paint.setColor(Color.parseColor("#ff0000"));
            } else {
                paint.setFakeBoldText(false);
                paint.setColor(Color.parseColor("#12A6C3"));
            }
            canvas.drawText(lettes[i], dx / 2, dy, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                eventTab(event.getY());
            }
            break;
            case MotionEvent.ACTION_MOVE: {
                eventTab(event.getY());
            }
            break;
            case MotionEvent.ACTION_UP: {
                postion = -1;
                if(callBack!=null){
                    callBack.setView();
                }

                invalidate();
            }
            break;

        }

        return true;
    }

    private void eventTab( float y) {
        int dex = (int) ((y - getPaddingTop()) / textH);
        if (dex < 0) {
            dex = 0;
        }
        if (dex > lettes.length - 1) {
            dex = lettes.length - 1;
        }

        //点击字母
        if(postion!=dex){
            Log.d("print", " ----postion-->"+postion);
            postion=dex;
            Log.d("print", " ----dex-->"+dex);
            if(callBack!=null){
                callBack.setText(dex,lettes[dex]);
            }

            invalidate();

        }

    }
}
