package cn.stu.cusview.ruiz.customeviewdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import cn.stu.cusview.ruiz.customeviewdemo.R;

public class TitleView extends View{

    private static final String Tag = TitleView.class.getSimpleName();


    private String mTitleStr;//
    private int mTitleColor;
    private int mTitleSize;

    private Paint mPaint;
    private Rect textRect;
    private float baseTextLine;

    private float density;

    public TitleView(Context context) {
        this(context,null);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs , defStyleAttr);
    }

    /***
     *初始化
     * */
    private void init(AttributeSet attributeSet, int defSty) {
        density = getResources().getDisplayMetrics().density;
        Log.e(Tag,"Display density="+density);
        int count = attributeSet.getAttributeCount();
        for (int poc = 0; poc<count; poc++){
            Log.e(Tag,"attrName="+attributeSet.getAttributeName(poc)+"  attrValue="+attributeSet.getAttributeValue(poc));
        }
        TypedArray array = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.TitleView,defSty,0);
        int arrCount = array.getIndexCount();
        for (int index = 0; index<arrCount; index++){
            int attr = array.getIndex(index);
            switch (attr){
                case R.styleable.TitleView_title:
                    mTitleStr = array.getString(index);
                    Log.e(Tag,"titleStr="+mTitleStr);
                    break;
                case R.styleable.TitleView_titleColor:
                    mTitleColor = array.getColor(index,Color.BLUE);
                    Log.e(Tag,"titleColor="+mTitleColor+"");
                    break;
                case R.styleable.TitleView_titleSize:
                    mTitleSize = array.getDimensionPixelSize(index, (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,16,getResources().getDisplayMetrics()));
                    Log.e(Tag,"titleSize="+mTitleSize);
                    break;
            }
        }


        if (mPaint == null){
            mPaint = new Paint();
            mPaint.setTextSize(mTitleSize);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(mTitleColor);
            mPaint.setAntiAlias(true);
        }

        textRect = new Rect();
        mPaint.getTextBounds(mTitleStr,0,mTitleStr.length(),textRect);
        float width = mPaint.measureText(mTitleStr,0,mTitleStr.length());
        textRect.left=0;
        textRect.right = (int)(width+0.5f);
        textRect.top=0;
        textRect.bottom = mTitleSize;
        baseTextLine = mPaint.getFontMetrics().ascent;
        Log.e(Tag,"text  top:right:bottom:left="+textRect.top+":"+textRect.right+":"+textRect.bottom+":"+textRect.left);
        Log.e(Tag,"Text width:height="+textRect.width()+":"+textRect.height());


        Log.e(Tag,"Display width:height="+getResources().getDisplayMetrics().widthPixels+":"+getResources().getDisplayMetrics().heightPixels);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = getSize(heightMeasureSpec,true);
        int width = getSize(widthMeasureSpec,false);
        Log.e(Tag,"measured Width:Height="+width+":"+height);
        setMeasuredDimension(width,height);
    }


    private int getSize(int measureSpec ,boolean height){
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if (height){
            Log.e(Tag,"Measuring height="+size);
        }else {
            Log.e(Tag,"Measuring Width="+size);
        }
        if (mode==MeasureSpec.EXACTLY){
            return size;
        }else {
            if (height) {
                return textRect.height() + getPaddingBottom() + getPaddingTop();
            }else {
                return textRect.width() + getPaddingLeft() + getPaddingRight();
            }
        }

    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawText(mTitleStr,(getWidth()-textRect.width())/2,(getHeight()-textRect.height())/2-(baseTextLine+mPaint.getFontMetrics().bottom-density) ,mPaint);

    }
}
