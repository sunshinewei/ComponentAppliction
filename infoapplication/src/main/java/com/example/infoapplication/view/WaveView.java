package com.example.infoapplication.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.Random;

public class WaveView extends View {

    public WaveView(Context context) {
        super(context);
        init();
    }


    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        onDrawLine(canvas);
    }

    private int height;
    private int width;
    private int controlX;
    private int controlY;
    private int waveHeight=100;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        height = MeasureSpec.getSize(heightMeasureSpec);

        width = MeasureSpec.getSize(widthMeasureSpec);

        setValueAnimator();
//        (widthMeasureSpec,heightMeasureSpec);
    }

    private void onDrawLine(Canvas canvas) {
        mPathLine.reset();
        mPathLine.moveTo(xoffset, waveHeight);
        for (int i = 1; i <=2; i++) {

            controlX = width * (2 * i - 1) / 4 + xoffset;
            if (i % 2 == 1) {
                mPathLine.quadTo(controlX, waveHeight-30, (width * (2 * i )) / 4 + xoffset, waveHeight);
            } else {
                mPathLine.quadTo(controlX, waveHeight+30, (width * (2 * i )) / 4 + xoffset, waveHeight);
            }
        }
//        canvas.drawPath(mPathLine, mPaintLine);

        mPathLine.moveTo(xoffset - width, waveHeight);
        for (int i = 1; i <= 2; i++) {

            controlX = width * (2 * i - 1) / 4 + xoffset - width;
            if (i % 2 == 1) {
                mPathLine.quadTo(controlX, waveHeight-30, (width * (2 * i)) / 4 + xoffset - width, waveHeight);
            } else {
                mPathLine.quadTo(controlX, waveHeight+30, (width * (2 * i)) / 4 + xoffset - width, waveHeight);
            }
        }
        canvas.drawPath(mPathLine, mPaintLine);
    }

    private int xoffset;

    private Paint mPaintLine;//线条
    private Path mPathLine;

    private void init() {

        mPaintLine = new Paint();
        mPaintLine.setColor(Color.RED);
        mPaintLine.setStrokeWidth(8);
        mPaintLine.setStyle(Paint.Style.STROKE);
        mPaintLine.setAntiAlias(true);
        mPathLine = new Path();

    }

    private void setValueAnimator() {
        ValueAnimator animator = new ValueAnimator();
        animator.setIntValues(0, width);
        animator.setDuration(3000);
        animator.setRepeatCount(-1);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                xoffset = (int) animatedValue;
//                createShader();
                System.out.println("changeddddd" + xoffset);
                invalidate();
            }
        });
        animator.start();
    }
}
