package com.reactnative.nativeUI;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by gechuanguang on 2017/3/13.
 * 邮箱：1944633835@qq.com
 */

public class MyPaintView extends View {
    /**
     * 画笔
     */
    private Paint mPaint;

    /**
     * 圆的半径
     */
    private float raduis=100;

    /**
     * view的宽和高
     */
    private int width;
    private int height;


    public MyPaintView(Context context) {
        this(context, null);
    }

    public MyPaintView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyPaintView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(50);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(width / 2, height / 2);
        canvas.drawCircle(0, 0, raduis, mPaint);
    }

    /**
     * 设置颜色
     */
    public void setColor(String color) {

        mPaint.setColor(Color.parseColor(color));
        invalidate();
    }

    /**
     * 设置圆的 半径
     */
    public void setRaduis(Integer raduis) {
        this.raduis = raduis;
        invalidate();
    }

}
