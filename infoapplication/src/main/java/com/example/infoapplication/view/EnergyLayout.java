package com.example.infoapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.infoapplication.view.utils.Utils;

public class EnergyLayout extends FrameLayout {

    private Context mCtx;

    public EnergyLayout(Context context) {
        super(context);
        mCtx = context;
    }

    public EnergyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCtx = context;
    }

    public EnergyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mCtx = context;
    }


    private int widthMode;
    private int heightMode;

    private int width;
    private int height;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        widthMode = MeasureSpec.getMode(widthMeasureSpec);
        heightMode = MeasureSpec.getMode(heightMeasureSpec);

        width = MeasureSpec.getSize(widthMeasureSpec);

        height = MeasureSpec.getSize(heightMeasureSpec);

        System.out.println("位置" + width + "              " + height);


//        measureChildren(width, height);


        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    private int left;
    private int top;

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        left = left;
        top = top;
        int childCount = getChildCount();
        if (childCount == 0) {

        } else {

            for (int i = 0; i < childCount; i++) {
                View childAt = getChildAt(i);

                int measuredHeight = childAt.getMeasuredHeight();
                int measuredWidth = childAt.getMeasuredWidth();
                int i1 = Utils.randomNumber(Math.abs(width - measuredWidth));
                int i2 = Utils.randomNumber(Math.abs(height - measuredHeight));

                System.out.println("子布局的位置" + i1 + "              " + i2);
                childAt.layout(i1, i2, i1 + measuredWidth, i2 + measuredHeight);
            }

        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void setAddEnergyListView(int size) {

        for (int i = 0; i < size; i++) {
            EnergyShowTestView energyShowView = new EnergyShowTestView(mCtx);
            addView(energyShowView);
        }
    }
}

