package com.example.ypaa.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import androidx.annotation.Nullable;
import androidx.core.view.animation.PathInterpolatorCompat;

public class TouchPullView extends View {
    private Paint mCirclePaint;
    private int mCircleRadius = 40;
    //进度值
    private float mProgress;
    //圆心坐标
    private float mCirclePointX, mCirclePointY;
    //可拖动高度
    private float mDragHeight = 300;
    //目标宽度
    private int mTargetWidth = 400;
    //贝塞尔曲线的路径和画笔
    private Path mPath = new Path();
    private Paint mPathPaint;
    //重心点最终高度，决定控制点的y坐标
    private int mTargetGravityHeight = 0;
    //角度变换，0-135度
    private int mTangetAngle = 100;

    private DecelerateInterpolator mProgressInterpolator = new DecelerateInterpolator();
    private Interpolator mTanentAngleInterpolator;

    public TouchPullView(Context context) {
        super(context);
        init();
    }

    public TouchPullView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TouchPullView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public TouchPullView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setDither(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(0x99ff0000);

        mCirclePaint = paint;

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setDither(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(0x99ff0000);

        mPathPaint = paint;

        mTanentAngleInterpolator = PathInterpolatorCompat.create((mCircleRadius * 2.0f) / mDragHeight,
                90.0f / mTangetAngle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        int iWidth = mCircleRadius << 1 + getPaddingLeft() + getPaddingRight();
        int iHeight = (int) ((mDragHeight * mProgress + 0.5f) + getPaddingTop() + getPaddingBottom());

        int measureWidth, measureHeight;

        if (widthMode == MeasureSpec.EXACTLY) {
            //确切的
            measureWidth = width;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //最多的
            measureWidth = Math.min(iWidth, width);
        } else {
            measureWidth = iWidth;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            //确切的
            measureHeight = height;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //最多的
            measureHeight = Math.min(iHeight, height);
        } else {
            measureHeight = iHeight;
        }
        setMeasuredDimension(measureWidth, measureHeight);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        updatePathLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int count = canvas.save();
        float tanX = (getWidth() - getValueByLine(getWidth(), mTargetWidth, mProgress)) / 2;
        canvas.translate(tanX, 0);
        canvas.drawPath(mPath, mPathPaint);

        canvas.drawCircle(mCirclePointX, mCirclePointY, mCircleRadius, mCirclePaint);

        canvas.restoreToCount(count);
    }

    public void setmProgress(float progress) {
        mProgress = progress;
        requestLayout();//通知onMeasure

    }

    /**
     * 高度变化时进行路径更新
     */
    private void updatePathLayout() {
        mCirclePointX = getWidth() >> 1;
        mCirclePointY = getHeight() >> 1;

        final float progress = mProgressInterpolator.getInterpolation(mProgress);

        //获取可绘制区域宽度和高度
        final float w = getValueByLine(getWidth(), mTargetWidth, mProgress);
        final float h = getValueByLine(0, mDragHeight, mProgress);

        final float cPointX = w / 2;
        final float cRadius = mCircleRadius;
        final float cPointY = h - cRadius;
        //控制点结束Y的值
        final float endControlY = mTargetGravityHeight;

        mCirclePointX = cPointX;
        mCirclePointY = cPointY;

        final Path path = mPath;
        //重置
        path.reset();
        path.moveTo(0, 0);

        //左边控制点
        float lEndPointX, lEndPointY;
        float lControlPointX, lControlPointY;
        //获取当前切线的弧度
        float angle = mTangetAngle*mTanentAngleInterpolator.getInterpolation(progress);
        double radian = Math.toRadians(angle);
        float x = (float) (Math.sin(radian) * cRadius);
        float y = (float) (Math.cos(radian) * cRadius);

        lEndPointX = cPointX - x;
        lEndPointY = cPointY + y;

        lControlPointY = getValueByLine(0, endControlY, progress);
        //控制点与结束点之间的高度
        float tHeight = lEndPointY - lControlPointY;
        Log.d("abcd", "updatePathLayout: " + tHeight);
        float tWidth = (float) (tHeight / Math.tan(radian));

        lControlPointX = lEndPointX - tWidth;


        path.quadTo(lControlPointX, lControlPointY, lEndPointX, lEndPointY);
        path.lineTo(cPointX + (cPointX - lEndPointX), lEndPointY);
        path.quadTo(cPointX + cPointX - lControlPointX, lControlPointY, w, 0);
    }

    /**
     * 获取当前值
     *
     * @return
     */
    private float getValueByLine(float start, float end, float progress) {
        return start + (end - start) * progress;
    }

    private ValueAnimator valueAnimator;

    /**
     * 释放之后的动画
     */
    public void release() {
        if (valueAnimator == null) {
            final ValueAnimator animator = ValueAnimator.ofFloat(mProgress, 0f);
            animator.setInterpolator(new DecelerateInterpolator());
            animator.setDuration(400);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    Object val = animator.getAnimatedValue();
                    if (val instanceof Float) {
                        setmProgress((Float) val);
                    }
                }
            });
            valueAnimator = animator;
        } else {
            valueAnimator.cancel();
            valueAnimator.setFloatValues(mProgress, 0f);
        }
        valueAnimator.start();
    }
}
