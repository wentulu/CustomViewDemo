package cn.stu.cusview.ruiz.customeviewdemo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class TouchEventView extends AppCompatTextView {

    private static final String TAG = TouchEventView.class.getSimpleName();
    private final boolean DEBUG = false;

    public TouchEventView(Context context) {
        super(context);
    }

    public TouchEventView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchEventView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (DEBUG)
            Log.e(TAG, "dispatchTouchEvent(MotionEvent event) "+event.getAction());
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (DEBUG)
            Log.e(TAG, "onTouchEvent(MotionEvent event) "+event.getAction());
        return super.onTouchEvent(event);
    }
}
