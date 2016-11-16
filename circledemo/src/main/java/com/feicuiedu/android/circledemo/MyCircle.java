package com.feicuiedu.android.circledemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MyCircle extends View {

    public static final int STROKE = 0;
    public static final int FILL = 1;

    /**
     * 画笔对象的引用
     */
    private Paint paint;
    /**
     * 圆环的颜色
     */
    private int roundColor;
    /**
     * 圆环进度的颜色
     */
    private int roundProgressColor;
    /**
     * 中间进度百分比的字符串的颜色
     */
    private int textColor;
    /**
     * 中间进度百分比的字符串的字体
     */
    private float textSize;
    /**
     * 圆环的宽度
     */
    private float roundWidth;
    /**
     * 最大进度
     */
    private int max;
    /**
     * 当前进度
     */
    private int progress;
    /**
     * 是否显示中间的进度
     */
    private boolean textIsDisplayable;
    /**
     * 进度的风格，实心或者空心
     */
    private int style;

    public MyCircle(Context context) {
        this(context, null);
        Log.d("(Context context)", "yes");
    }

    public MyCircle(Context context, AttributeSet attrs) {

        this(context, attrs, 0);
        Log.d("(context,attrs)", "yes");
    }

    public MyCircle(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        Log.d("(ctx,attrs,defStyle)", "yes");
        paint = new Paint();


        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyCircle);
        roundColor = ta.getColor(R.styleable.MyCircle_roundColor, Color.RED);
        roundProgressColor = ta.getColor(R.styleable.MyCircle_roundProgressColor, Color.GREEN);
        textColor = ta.getColor(R.styleable.MyCircle_textColor, Color.GREEN);
        textSize = ta.getDimension(R.styleable.MyCircle_textSize, 15);
        roundWidth = ta.getDimension(R.styleable.MyCircle_roundWidth, 5);
        max = ta.getInteger(R.styleable.MyCircle_max, 100);
        textIsDisplayable = ta.getBoolean(R.styleable.MyCircle_textIsDisplayable, true);
        style = ta.getInt(R.styleable.MyCircle_style, 0);
        Log.d("max", max + "");

        // 静态实例 回收内存
        ta.recycle();
        Log.d("max_after", max + "");
    }

    public static int getStroke() {
        return STROKE;
    }

    public static int getFill() {
        return FILL;
    }

    public synchronized int getMax() {
        return max;
    }

    /**
     * 设置进度的最大值
     *
     * @param max
     */
    public synchronized void setMax(int max) {
        if (max < 0) {
            throw new IllegalArgumentException("max not less than 0");
        }
        this.max = max;
    }

    /**
     * 获取进度.需要同步
     *
     * @return
     */
    public synchronized int getProgress() {
        return progress;
    }

    /**
     * 设置进度，此为线程安全控件，由于考虑多线的问题，需要同步
     * 刷新界面调用postInvalidate()能在非UI线程刷新
     *
     * @param progress
     */
    public synchronized void setProgress(int progress) {
        if (progress < 0) {
            throw new IllegalArgumentException("progress not less than 0");
        }
        if (progress > max) {
            progress = max;
        }
        if (progress <= max) {
            this.progress = progress;
            postInvalidate();
        }

    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public int getRoundColor() {
        return roundColor;
    }

    public void setRoundColor(int roundColor) {
        this.roundColor = roundColor;
    }

    public int getRoundProgressColor() {
        return roundProgressColor;
    }

    public void setRoundProgressColor(int roundProgressColor) {
        this.roundProgressColor = roundProgressColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public float getRoundWidth() {
        return roundWidth;
    }

    public void setRoundWidth(float roundWidth) {
        this.roundWidth = roundWidth;
    }

    public boolean isTextIsDisplayable() {
        return textIsDisplayable;
    }

    public void setTextIsDisplayable(boolean textIsDisplayable) {
        this.textIsDisplayable = textIsDisplayable;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);

        paint = new Paint();
        /**
         * 画最外层的大圆环
         */
        int centre = getWidth() / 2; // 获取圆心的x坐标
        int radius = (int) (centre - roundWidth / 2); // 圆环的半径
        paint.setColor(roundColor); // 设置圆环的颜色
        paint.setStyle(Paint.Style.STROKE); // 设置空心
        paint.setStrokeWidth(roundWidth); // 设置圆环的宽度 设置线宽
        paint.setAntiAlias(true); // 消除锯齿
        canvas.drawCircle(centre, centre, radius, paint); // 画出圆环



        /**
         * 画进度百分比
         */
        paint.setStrokeWidth(0);
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setTypeface(Typeface.DEFAULT_BOLD); // 设置字体

        double percent = (((float) progress / (float) max) * 100); // 中间的进度百分比

        float textWidth = paint.measureText(percent + "%"); // 测量字体宽度，我们需要根据字体的宽度设置在圆环中间

        //if (textIsDisplayable && percent != 0 && style == STROKE) {
        canvas.drawText(percent + "%", centre - textWidth / 2, centre + textSize / 2, paint); // 画出进度百分比
        //}

        /**
         * 画圆弧 ，画圆环的进度
         */

        // 设置进度是实心还是空心
        paint.setStrokeWidth(roundWidth); // 设置圆环的宽度
        paint.setColor(roundProgressColor); // 设置进度的颜色
        // 用于定义的圆弧的形状和大小的界限
        RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius);

        switch (style) {
            case STROKE:
                paint.setStyle(Paint.Style.STROKE);
                //canvas.drawArc(oval, 0, 360 * progress / max, false, paint); // 根据进度画圆弧
                break;

            case FILL:
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                if (progress != 0)

                break;

        }
        canvas.drawArc(oval, 0, 360 * progress / max, true, paint); // 根据进度画圆弧
    }

}
