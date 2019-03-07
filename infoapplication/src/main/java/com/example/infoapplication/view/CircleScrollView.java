package com.example.infoapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.infoapplication.R;

public class CircleScrollView extends ViewGroup {

    private SurfaceViewHelper mSurfaceViewHelper;

    public CircleScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);


    }

    public CircleScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mSurfaceViewHelper = new SurfaceViewHelper();

    }

    public CircleScrollView(Context context) {
        super(context);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int childCount = getChildCount();
        if (childCount == 0) {

        } else {
            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);

                childView.layout(getWidth() / 2, getHeight() / 2, getWidth() / 2 + childView.getMeasuredWidth(), getHeight() / 2 + childView.getMeasuredHeight());

            }
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);


        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int childWidth = 0;
        int childHeight = 0;

        for (int i = 0; i < getChildCount(); i++) {

            Log.e("子布局",getChildCount()+"");
            if (widthMode != MeasureSpec.UNSPECIFIED) {
                childWidth = getChildAt(i).getMeasuredWidth();
            } else {
                childWidth = getChildAt(i).getMeasuredWidth();
            }
            if (heightMode != MeasureSpec.UNSPECIFIED) {
                childHeight = getChildAt(i).getMeasuredHeight();
            } else {
                childHeight = getChildAt(i).getMeasuredHeight();
            }
            setMeasuredDimension(childWidth, childHeight);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {


        onDrawCircle(canvas);

        super.onDraw(canvas);
    }

    /*
     * 画中心圆形
     */
    private void onDrawCircle(Canvas canvas) {
        Paint mPaintCircle = new Paint();
        mPaintCircle.setStyle(Paint.Style.STROKE);//画笔属性是空心圆
        mPaintCircle.setStrokeWidth(8);//设置画笔粗细
        mPaintCircle.setColor(Color.RED);

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 200, mPaintCircle);
    }


    protected class SurfaceViewHelper {


        private Paint mPaintCircle;//中心圆

        SurfaceViewHelper() {
            init();
        }

        private void init() {
            mPaintCircle = new Paint();
            mPaintCircle.setStyle(Paint.Style.STROKE);//画笔属性是空心圆
            mPaintCircle.setStrokeWidth(8);//设置画笔粗细
            mPaintCircle.setColor(Color.RED);
        }


    }


}
