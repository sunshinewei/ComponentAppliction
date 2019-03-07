package com.example.infoapplication.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

import com.example.infoapplication.view.utils.PointEntity;
import com.example.infoapplication.view.utils.Utils;

import java.util.ArrayList;

public class EnergyShowTestView extends View {

    //收集页面宽度
    private int width;
    //手机页面高度
    private int height;

    private Context mCtx;

    public EnergyShowTestView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mCtx = context;

        init();
    }

    public EnergyShowTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public EnergyShowTestView(Context context) {
        super(context);
        mCtx = context;
        init();
    }


    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);

        onDrawWater(canvas);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        int widthModel = MeasureSpec.getMode(widthMeasureSpec);

        int heightModel = MeasureSpec.getMode(heightMeasureSpec);

//        width = widthModel == MeasureSpec.UNSPECIFIED ? 300 : getMeasuredWidth();
//
//        height = heightModel == MeasureSpec.UNSPECIFIED ? 300 : getMeasuredHeight();


        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    /**
     * 小水滴区域
     *
     * @param canvas
     */
    private void onDrawWater(Canvas canvas) {

        mCanvas = canvas;
        if (mPointEntityArray != null && mPointEntityArray.size() > 0) {
            for (int i = 0; i < mPointEntityArray.size(); i++) {
                canvas.drawCircle(mPointEntityArray.get(i).getX(), mPointEntityArray.get(i).getY(), 50, mPaintWater);

                canvas.drawText("1.5312", mPointEntityArray.get(i).getX() - 50, mPointEntityArray.get(i).getY() + 75, mPaintBottomText);


//                if (mPointEntityArray.get(i).isPoint()) {
//
//                    Toast.makeText(mCtx, "画线", Toast.LENGTH_SHORT).show();
//
//                    onDrawLine(canvas);
//                }
            }
        } else {
            for (int i = 0; i < 1; i++) {
                int x = 80;
                int y = 80;
                canvas.drawCircle(x, y, 50, mPaintWater);
                canvas.drawText("1.5312", x - 50, y + 75, mPaintBottomText);
                mPointEntityArray.add(new PointEntity(x, y, false));

            }
        }

//        onDrawText(canvas);
    }

    /**
     * 绘制文本内容
     *
     * @param canvas
     */
    private int startX = 50;
    private int startY = 50;

    private void onDrawText(Canvas canvas) {

        canvas.drawText("收集", startX, startY, mPaintText);


    }

    /**
     * 绘制贝瑟尔曲线  绘制收集水滴
     *
     * @param canvas
     */
//    private void onDrawLine(Canvas canvas) {
//
//        mPathLine.moveTo(startX, startY);
//        if (mInterpolatedTime <= 250) {
//
//            mPathLine.quadTo(Math.abs(endX - startX) / 2, Math.abs(endY - startY) / 2, endX - Math.abs(endX - startX) / 4, endY - Math.abs(endY - startY) / 4);
//
//        } else if (mInterpolatedTime > 250 && mInterpolatedTime <= 500) {
//
//            mPathLine.quadTo(Math.abs(endX - startX) / 2, Math.abs(endY - startY) / 2, endX - (Math.abs(endX - startX) / 4) * 2, (endY - Math.abs(endY - startY) / 4) * 2);
//        } else if (mInterpolatedTime > 500 && mInterpolatedTime <= 750) {
//
//            mPathLine.quadTo(Math.abs(endX - startX) / 2, Math.abs(endY - startY) / 2, endX - (Math.abs(endX - startX) / 4) * 3, (endY - Math.abs(endY - startY) / 4) * 3);
//        } else if (mInterpolatedTime > 750 && mInterpolatedTime <= 1000) {
//
//            mPathLine.quadTo(Math.abs(endX - startX) / 2, Math.abs(endY - startY) / 2, endX - Math.abs(endX - startX), endY - Math.abs(endY - startY));
//        }
//
//
//        canvas.drawPath(mPathLine, mPaintLine);
//
//    }


    private Paint mPaintWater;
    private Paint mPaintText;
    private Paint mPaintBottomText;

    private Paint mPaintLine;//线条
    private Path mPathLine;

    private Canvas mCanvas;


    private ArrayList<PointEntity> mPointEntityArray;

    private void init() {
        mPaintWater = new Paint();
        mPaintWater.setColor(Color.GREEN);

        mPaintText = new Paint();
        mPaintText.setColor(Color.BLUE);
        mPaintText.setTextSize(50);

        mPaintBottomText = new Paint();
        mPaintBottomText.setTextSize(30);
        mPaintBottomText.setColor(Color.RED);


        mPaintLine = new Paint();
        mPaintLine.setColor(Color.RED);
        mPaintLine.setStrokeWidth(8);
        mPaintLine.setStyle(Paint.Style.STROKE);

        mPathLine = new Path();

        mPointEntityArray = new ArrayList<>();
    }

//    @Override
//    protected void onVisibilityChanged(View changedView, int visibility) {
//        super.onVisibilityChanged(changedView, visibility);
//        if (visibility==VISIBLE){
//            start();
//        }
//
//    }

//    int endX, endY;
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
////                for (int i = 0; i < mPointEntityArray.size(); i++) {
////                    if (((event.getX() - 50) < mPointEntityArray.get(i).getX() && mPointEntityArray.get(i).getX() < (event.getX() + 50)) &&
////                            event.getY() - 50 < mPointEntityArray.get(i).getY() && mPointEntityArray.get(i).getY() < event.getY() + 50) {
////
////                    }
////                }
//                return true;
//
//            case MotionEvent.ACTION_UP:
//                for (int i = 0; i < mPointEntityArray.size(); i++) {
//                    PointEntity pointEntity = mPointEntityArray.get(i);
//                    if (((event.getX() - 50) < mPointEntityArray.get(i).getX() && mPointEntityArray.get(i).getX() < (event.getX() + 50)) &&
//                            event.getY() - 50 < mPointEntityArray.get(i).getY() && mPointEntityArray.get(i).getY() < event.getY() + 50) {
//
//                        if (mOnDownClickListener != null) {
//                            mOnDownClickListener.onClickListener();
//
//                        }
//
//                        mPointEntityArray.get(i).setPoint(true);
//
//
//                        if (mInterpolatedTime <= 250) {
//
//                            endY = mPointEntityArray.get(i).getY();
//                            endX = mPointEntityArray.get(i).getX();
//
//                        } else if (mInterpolatedTime > 250 && mInterpolatedTime <= 500) {
//
//                            endX = endX - (Math.abs(endX - startX) / 4) * 2;
//                            endY = endY - (Math.abs(endY - startY) / 4) * 2;
//                        } else if (mInterpolatedTime > 500 && mInterpolatedTime <= 750) {
//
//                            endX = endX - (Math.abs(endX - startX) / 4) * 3;
//                            endY = endY - (Math.abs(endY - startY) / 4) * 3;
//                        } else if (mInterpolatedTime > 750 && mInterpolatedTime <= 1000) {
//
//                            endX = endX - Math.abs(endX - startX);
//                            endY = endY - Math.abs(endY - startY);
//                        }
////                        mPointEntityArray.remove(pointEntity);
//                        invalidate();
//                    }
//                }
//                return true;
//        }
//
//
//        return super.onTouchEvent(event);
//
//    }

    private OnDownClickListener mOnDownClickListener;

    public void setmOnDownClickListener(OnDownClickListener mOnDownClickListener) {
        if (mOnDownClickListener != null) {
            this.mOnDownClickListener = mOnDownClickListener;
        }

    }

    public interface OnDownClickListener {
        void onClickListener();
    }

    /**
     * 曲线动画
     */

    private Transformation transformation;

    private float mInterpolatedTime;

    class LineAnimation extends Animation {
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            mInterpolatedTime = interpolatedTime;
            invalidate();
        }
    }

    ObjectAnimator mAnimator;
    public void start() {
        if (mAnimator == null) {
            mAnimator = ObjectAnimator.ofFloat(this, "translationY", -6.0f, 6.0f, -6.0f);
            mAnimator.setDuration(3500);
            mAnimator.setInterpolator(new LinearInterpolator());
            mAnimator.setRepeatMode(ValueAnimator.RESTART);
            mAnimator.setRepeatCount(ValueAnimator.INFINITE);
            mAnimator.start();
        } else if (!mAnimator.isStarted()) {
            mAnimator.start();
        }
    }


}
