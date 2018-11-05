package cn.stu.cusview.ruiz.customeviewdemo.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class TouchEventViewGroup extends FrameLayout {

    private static final String TAG = TouchEventViewGroup.class.getSimpleName();
    private final boolean DEBUG = false;

    public TouchEventViewGroup(@NonNull Context context) {
        super(context);

    }

    public TouchEventViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchEventViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (DEBUG)
            Log.e(TAG, "dispatchTouchEvent(MotionEvent ev) "+ev.getAction());
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (DEBUG)
            Log.e(TAG, "onInterceptTouchEvent(MotionEvent ev) "+ev.getAction());
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (DEBUG)
            Log.e(TAG, "onTouchEvent(MotionEvent event) "+event.getAction());
        return super.onTouchEvent(event);
    }
}
