package com.android.cc.customviewpractise.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.android.cc.customviewpractise.R;


/**
 * Created by Chenwei
 * description: 购物车显示已购买数量的view
 * date:2017/1/5
 **/
public class CircleNumView extends View {
    private int largerCircleRadius; //大圆的半径(xml)
    private int loopRadius; // 圆环半径(xml)
    private int smallCircleRadius; // 小圆半径
    private int outerColor; //外层部分颜色(xml)
    private int innerColor; //内层部分颜色(xml)
    private int smallOuterColor;
    private int smallInnterColor;
    private int iconResId; //图片资源id(xml)
    private int numColor; //数字颜色(xml)
    private int numTextSize; //数字大小(xml)

    private Paint innerPaint;
    private Paint outerPaint;
    private Paint numPaint;
    private Paint smallerInnerPaint;
    private Paint smallerOuterPaint;

    private int num;

    public CircleNumView(Context context) {
        super(context);
    }

    public CircleNumView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }

    public CircleNumView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        getAttrs(context, attrs);
        smallCircleRadius = (int)(largerCircleRadius* 0.4);
        initPaint();
    }

    private void getAttrs(Context context, AttributeSet attrs){
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CircleNumView);
        largerCircleRadius = ta.getDimensionPixelOffset(R.styleable.CircleNumView_largerCircleRadius, 66);
        loopRadius = ta.getDimensionPixelOffset(R.styleable.CircleNumView_loopRadius, 20);
        outerColor = ta.getColor(R.styleable.CircleNumView_outerColor, ContextCompat.getColor(getContext(), R.color.colorAccent ));
        innerColor = ta.getColor(R.styleable.CircleNumView_innerColor, ContextCompat.getColor(getContext(), R.color.colorPrimary));
        numColor = ta.getColor(R.styleable.CircleNumView_numColor, ContextCompat.getColor(getContext(), R.color.colorPrimary));
        numTextSize = ta.getDimensionPixelOffset(R.styleable.CircleNumView_numTextSize, 40);
        smallOuterColor = ta.getColor(R.styleable.CircleNumView_smallOuterColor, ContextCompat.getColor(getContext(), R.color.colorPrimary));
        smallInnterColor = ta.getColor(R.styleable.CircleNumView_smallInnterColor,ContextCompat.getColor(context, R.color.white));
        iconResId = ta.getResourceId(R.styleable.CircleNumView_iconResId,0);
        num = ta.getInteger(R.styleable.CircleNumView_num, 0);
        ta.recycle();
    }

    private void initPaint(){
        innerPaint = new Paint();
        innerPaint.setAntiAlias(true);
        innerPaint.setColor(innerColor);
        innerPaint.setStyle(Paint.Style.FILL);

        outerPaint = new Paint();
        outerPaint.setAntiAlias(true);
        outerPaint.setColor(outerColor);
        outerPaint.setStyle(Paint.Style.STROKE);
        outerPaint.setStrokeWidth(loopRadius);

        smallerInnerPaint = new Paint();
        smallerInnerPaint.setAntiAlias(true);
        smallerInnerPaint.setColor(smallInnterColor);
        smallerInnerPaint.setStyle(Paint.Style.FILL);

        smallerOuterPaint = new Paint();
        smallerOuterPaint.setAntiAlias(true);
        smallerOuterPaint.setColor(smallOuterColor);
        smallerOuterPaint.setStrokeWidth(loopRadius / 4);
        smallerOuterPaint.setStyle(Paint.Style.STROKE);

        numPaint = new Paint();
        numPaint.setColor(numColor);
        numPaint.setTextSize(numTextSize);
        numPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int computeHeight = largerCircleRadius * 2 + smallCircleRadius;
        int computeWidth = largerCircleRadius * 2;
        int width = resolveSize(computeWidth, widthMeasureSpec);

        //根据宽度修正高度(因为layout_height设置的高度只是大圆的高度，未考虑小圆的高度)
        //实际高度为大圆直径高度 + 小圆半径高度 = 大圆直径高度 + 大圆直径高度 * 0.4 / 2
        int smallheight = (int)(width  * 0.4);
        int smallCR = smallheight / 2;
        int height = width + smallCR;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint bgPaint = new Paint();
        bgPaint.setColor(ContextCompat.getColor(getContext(), android.R.color.holo_red_dark));
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), bgPaint);

        //画布宽度
        int width = canvas.getWidth();

        //大圆半径
        int lcRadius = width / 2;
        //小圆半径
        int scRadius = (int)(lcRadius * 0.4);


        //大圆
        int bigCX =lcRadius;
        int bigCY = bigCX + scRadius;
        canvas.drawCircle(bigCX, bigCY, lcRadius - loopRadius , innerPaint);
        canvas.drawCircle(bigCX,bigCY, lcRadius - loopRadius, outerPaint);

        //小圆
        int smallCX = width / 2 + scRadius / 2 + scRadius;
        int smallCY = scRadius;
        canvas.drawCircle(smallCX, smallCY, scRadius  - loopRadius / 4, smallerInnerPaint);
        canvas.drawCircle(smallCX, smallCY, scRadius  - loopRadius / 4 , smallerOuterPaint);

        //数字
        String numText = String.valueOf(num);
        int numLen = (int)numPaint.measureText(numText);
        int numX = smallCX - numLen / 2;
        Paint.FontMetrics fontMetrics = numPaint.getFontMetrics();
        int numY = smallCY +  (int)(Math.abs(fontMetrics.ascent) - fontMetrics.descent) / 2;
        canvas.drawText(numText, numX, numY, numPaint);

        if(iconResId == 0){
            return;
        }

        //图片
        BitmapDrawable drawable = (BitmapDrawable)ContextCompat.getDrawable(getContext(), iconResId);

        Bitmap bitmap = drawable.getBitmap();
        int bmpWidth = bitmap.getWidth();
        int bmpHeight = bitmap.getHeight();
        int reqIconWidth = (int)(width * 0.4);
        int reqIconHeight = reqIconWidth * bmpHeight / bmpWidth;

        bitmap = Bitmap.createScaledBitmap(bitmap, reqIconWidth, reqIconHeight, true);
        int bl = (width - reqIconWidth) / 2;
        int bt = (width - reqIconHeight) / 2 + scRadius;
        canvas.drawBitmap(bitmap, bl, bt, null);
    }

    public void setNum(int num){
        this.num = num;
        invalidate();
    }

    public int getNum(){
        return num;
    }
}
