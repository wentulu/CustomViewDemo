package cn.stu.cusview.ruiz.customeviewdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import cn.stu.cusview.ruiz.customeviewdemo.R;


/**
 * 第一个自定义View主要测试一些实现
 * 1、自定义属性
 * 2、尝试图片数组
 * 3、文字的宽高测量，以及基线的获取
 */
public class TitleView extends View {

    private static final String Tag = TitleView.class.getSimpleName();

    private static final boolean DEBUG = true;//日志控制   true的时候打印日志

    private String mTitleStr;//
    private int mTitleColor;//
    private int mTitleSize;//

    private Paint mPaint;//画笔
    private Rect textRect;//文字区域
    private float baseTextLine;//基线的位置，那就是ascent的

    private float density;//屏幕密度

    public TitleView(Context context) {
        this(context, null);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    /***
     *初始化
     * */
    private void init(AttributeSet attributeSet, int defSty) {
        density = getResources().getDisplayMetrics().density;//
        if (DEBUG)
            Log.e(Tag, "Display density=" + density);
        int count = attributeSet.getAttributeCount();
        if (DEBUG)
            for (int poc = 0; poc < count; poc++) {
                Log.e(Tag, "attrName=" + attributeSet.getAttributeName(poc) + "  attrValue=" + attributeSet.getAttributeValue(poc));
            }
        TypedArray array = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.TitleView, defSty, 0);
        int arrCount = array.getIndexCount();
        for (int index = 0; index < arrCount; index++) {
            int attr = array.getIndex(index);
            switch (attr) {
                case R.styleable.TitleView_title://测试字符串获取
                    mTitleStr = array.getString(index);
                    if (DEBUG)
                        Log.e(Tag, "titleStr=" + mTitleStr);
                    break;
                case R.styleable.TitleView_titleColor://测试颜色获取
                    mTitleColor = array.getColor(index, Color.BLUE);
                    if (DEBUG)
                        Log.e(Tag, "titleColor=" + mTitleColor + "");
                    break;
                case R.styleable.TitleView_titleSize://测试尺寸获取
                    mTitleSize = array.getDimensionPixelSize(index, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    if (DEBUG)
                        Log.e(Tag, "titleSize=" + mTitleSize);
                    break;
                case R.styleable.TitleView_stringArr://字符串数组
                    int id = array.getResourceId(index, 0);
                    String[] strings = getResources().getStringArray(id);
                    if (DEBUG)
                        Log.e(Tag, "StringArry0=" + strings[0]);
                    break;
                case R.styleable.TitleView_drawableArr://测试Drawable的数组获取
                    int draId = array.getResourceId(index, 0);
                    TypedArray drawablesaa = getResources().obtainTypedArray(draId);//这个是关键
                    int len = drawablesaa.length();
                    int[] drawablesId = new int[len];
                    for (int i = 0; i < len; i++) {
                        drawablesId[i] = drawablesaa.getResourceId(i, 0);
                    }
                    Drawable drawable = getResources().getDrawable(drawablesId[0]);
                    if (DEBUG) {
                        String drawableName = getResources().getResourceName(drawablesId[0]);
                        Log.e(Tag, "drawableName=" + drawableName);
                    }
                    setBackground(drawable);
                    break;
            }
        }


        if (mPaint == null) {//画笔设置
            mPaint = new Paint();
            mPaint.setTextSize(mTitleSize);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(mTitleColor);
            mPaint.setAntiAlias(true);
        }

        //记录文字的实际展示区域
        textRect = new Rect();
        mPaint.getTextBounds(mTitleStr, 0, mTitleStr.length(), textRect);//测试出来的宽高稍微小些
        float width = mPaint.measureText(mTitleStr, 0, mTitleStr.length());//测量出实际的宽度
        textRect.left = 0;
        textRect.right = (int) (width + 0.5f);
        textRect.top = 0;
        textRect.bottom = mTitleSize;//高度和文字的高度一致 测量出来的bound会小了一个像素

        //
        baseTextLine = mPaint.getFontMetrics().ascent;
        if (DEBUG) {
            Log.e(Tag, "text  top:right:bottom:left=" + textRect.top + ":" + textRect.right + ":" + textRect.bottom + ":" + textRect.left);
            Log.e(Tag, "Text width:height=" + textRect.width() + ":" + textRect.height());
            Log.e(Tag, "Display width:height=" + getResources().getDisplayMetrics().widthPixels + ":" + getResources().getDisplayMetrics().heightPixels);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = getSize(heightMeasureSpec, true);
        int width = getSize(widthMeasureSpec, false);
        if (DEBUG)
            Log.e(Tag, "measured Width:Height=" + width + ":" + height);
        setMeasuredDimension(width, height);
    }

    /**
     *
     * 测量宽高使用的信息
     * */
    private int getSize(int measureSpec, boolean height) {
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if (DEBUG) {
            if (height) {
                Log.e(Tag, "Measuring height=" + size);
            } else {
                Log.e(Tag, "Measuring Width=" + size);
            }
        }
        if (mode == MeasureSpec.EXACTLY) {
            return size;
        } else {
            if (height) {
                return textRect.height() + getPaddingBottom() + getPaddingTop();
            } else {
                return textRect.width() + getPaddingLeft() + getPaddingRight();
            }
        }

    }


    @Override
    protected void onDraw(Canvas canvas) {
        //绘制的时候基线应该稍微往下移动一个密度的高度可能由于四舍五入造成
        canvas.drawText(mTitleStr, (getWidth() - textRect.width()) / 2, (getHeight() - textRect.height()) / 2 - (baseTextLine + mPaint.getFontMetrics().bottom - density), mPaint);

    }
}
