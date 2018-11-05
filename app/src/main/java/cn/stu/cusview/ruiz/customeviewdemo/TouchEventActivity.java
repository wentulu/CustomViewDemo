package cn.stu.cusview.ruiz.customeviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;



/**
 * 日志在下方
 * 1、可以看出DOWN事件会传完全程
 * 2、事件一旦在某处被拦截，那么直到UP发生，所有的事件都会被拦截
 * 3、在ViewGroup中onIntercept返回true，事件不会经过View，但是DOWN事件还是会经过onTouch传递给Activity
 * 4、在ViewGroup中的dispatchTouchEvent中返回true，那么onTouch不会被执行，且事件也不会经过View
 * 5、在View的dispatchTouchEvent中返回true，所有的onTouch不会被执行，设置的点击事件不会被执行
 * 6、一旦ViewGroup的dispatch返回了true或者false那么onIntercept就不会执行
 * 7、dispatch一旦直接返回true或者false，那么本身的onTouch不会执行，且如果是true事件到此为止，为false那么有父容器onTouch
 * */
public class TouchEventActivity extends AppCompatActivity {

    private static final String TAG = TouchEventActivity.class.getSimpleName();
    private final boolean DEBUG = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (DEBUG)
            Log.e(TAG, "onTouchEvent(MotionEvent event) "+event.getAction());
        return super.onTouchEvent(event);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (DEBUG)
            Log.e(TAG, "dispatchTouchEvent(MotionEvent ev) "+ev.getAction());
        return super.dispatchTouchEvent(ev);
    }


    public void clickTouchView(View view){
        Toast.makeText(this,"clickTouchView",Toast.LENGTH_SHORT).show();
    }


    public void clickTouchViewGroup(View view){
        Toast.makeText(this,"click TouchViewGroup",Toast.LENGTH_SHORT).show();
    }

}
/**
 1、点击ViewGroup的日志，可以发现group的事件回调只执行了一遍，且只有Down事件
 E/TouchEventActivity: dispatchTouchEvent(MotionEvent ev) 0
 E/TouchEventViewGroup: dispatchTouchEvent(MotionEvent ev) 0
 onInterceptTouchEvent(MotionEvent ev) 0
 onTouchEvent(MotionEvent event) 0
 E/TouchEventActivity: onTouchEvent(MotionEvent event) 0
 E/TouchEventActivity: dispatchTouchEvent(MotionEvent ev) 2
 E/TouchEventActivity: onTouchEvent(MotionEvent event) 2
 E/TouchEventActivity: dispatchTouchEvent(MotionEvent ev) 2
 E/TouchEventActivity: onTouchEvent(MotionEvent event) 2
 E/TouchEventActivity: dispatchTouchEvent(MotionEvent ev) 2
 E/TouchEventActivity: onTouchEvent(MotionEvent event) 2
 E/TouchEventActivity: dispatchTouchEvent(MotionEvent ev) 2
 onTouchEvent(MotionEvent event) 2
 E/TouchEventActivity: dispatchTouchEvent(MotionEvent ev) 2
 E/TouchEventActivity: onTouchEvent(MotionEvent event) 2
 E/TouchEventActivity: dispatchTouchEvent(MotionEvent ev) 2
 E/TouchEventActivity: onTouchEvent(MotionEvent event) 2
 E/TouchEventActivity: dispatchTouchEvent(MotionEvent ev) 1
 E/TouchEventActivity: onTouchEvent(MotionEvent event) 1

 2、点击了View
 E/TouchEventActivity: dispatchTouchEvent(MotionEvent ev) 0
 E/TouchEventViewGroup: dispatchTouchEvent(MotionEvent ev) 0
 onInterceptTouchEvent(MotionEvent ev) 0
 E/TouchEventView: dispatchTouchEvent(MotionEvent event)
 onTouchEvent(MotionEvent event)
 E/TouchEventViewGroup: onTouchEvent(MotionEvent event) 0
 E/TouchEventActivity: onTouchEvent(MotionEvent event) 0
 E/TouchEventActivity: dispatchTouchEvent(MotionEvent ev) 2
 E/TouchEventActivity: onTouchEvent(MotionEvent event) 2
 E/TouchEventActivity: dispatchTouchEvent(MotionEvent ev) 2
 E/TouchEventActivity: onTouchEvent(MotionEvent event) 2
 E/TouchEventActivity: dispatchTouchEvent(MotionEvent ev) 2
 E/TouchEventActivity: onTouchEvent(MotionEvent event) 2
 E/TouchEventActivity: dispatchTouchEvent(MotionEvent ev) 2
 E/TouchEventActivity: onTouchEvent(MotionEvent event) 2
 E/TouchEventActivity: dispatchTouchEvent(MotionEvent ev) 2
 E/TouchEventActivity: onTouchEvent(MotionEvent event) 2
 E/TouchEventActivity: dispatchTouchEvent(MotionEvent ev) 2
 E/TouchEventActivity: onTouchEvent(MotionEvent event) 2
 E/TouchEventActivity: dispatchTouchEvent(MotionEvent ev) 2
 E/TouchEventActivity: onTouchEvent(MotionEvent event) 2
 E/TouchEventActivity: dispatchTouchEvent(MotionEvent ev) 1
 E/TouchEventActivity: onTouchEvent(MotionEvent event) 1

 3、对TouchView添加点击事件。可以看到所有的事件传递到View之后不会再由onTouch在传递给父容器
 /TouchEventActivity: dispatchTouchEvent(MotionEvent ev) 0
 /TouchEventViewGroup: dispatchTouchEvent(MotionEvent ev) 0
                        onInterceptTouchEvent(MotionEvent ev) 0
 /TouchEventView: dispatchTouchEvent(MotionEvent event) 0
                    onTouchEvent(MotionEvent event) 0

 /TouchEventActivity: dispatchTouchEvent(MotionEvent ev) 2
 /TouchEventViewGroup: dispatchTouchEvent(MotionEvent ev) 2
                onInterceptTouchEvent(MotionEvent ev) 2
 /TouchEventView: dispatchTouchEvent(MotionEvent event) 2
 onTouchEvent(MotionEvent event) 2

 /TouchEventActivity: dispatchTouchEvent(MotionEvent ev) 2
 /TouchEventViewGroup: dispatchTouchEvent(MotionEvent ev) 2
 onInterceptTouchEvent(MotionEvent ev) 2
 /TouchEventView: dispatchTouchEvent(MotionEvent event) 2
 onTouchEvent(MotionEvent event) 2

 /TouchEventActivity: dispatchTouchEvent(MotionEvent ev) 1
 /TouchEventViewGroup: dispatchTouchEvent(MotionEvent ev) 1
 onInterceptTouchEvent(MotionEvent ev) 1
 /TouchEventView: dispatchTouchEvent(MotionEvent event) 1
 onTouchEvent(MotionEvent event) 1


 4、对TouchViewGroup设置点击事件，点击TouchViewGoup
  E/TouchEventActivity: dispatchTouchEvent(MotionEvent ev) 0
  E/TouchEventViewGroup: dispatchTouchEvent(MotionEvent ev) 0
                        onInterceptTouchEvent(MotionEvent ev) 0
                        onTouchEvent(MotionEvent event) 0

  E/TouchEventActivity: dispatchTouchEvent(MotionEvent ev) 2
  E/TouchEventViewGroup: dispatchTouchEvent(MotionEvent ev) 2
                        onTouchEvent(MotionEvent event) 2
  E/TouchEventActivity: dispatchTouchEvent(MotionEvent ev) 2
  E/TouchEventViewGroup: dispatchTouchEvent(MotionEvent ev) 2
                        onTouchEvent(MotionEvent event) 2
  E/TouchEventActivity: dispatchTouchEvent(MotionEvent ev) 2
  E/TouchEventViewGroup: dispatchTouchEvent(MotionEvent ev) 2
                        onTouchEvent(MotionEvent event) 2
  E/TouchEventActivity: dispatchTouchEvent(MotionEvent ev) 1
  E/TouchEventViewGroup: dispatchTouchEvent(MotionEvent ev) 1
  E/TouchEventViewGroup: onTouchEvent(MotionEvent event) 1

 5、对TouchViewGroup设置点击事件，点击TouchView
 E/TouchEventActivity: dispatchTouchEvent(MotionEvent ev) 0
 E/TouchEventViewGroup: dispatchTouchEvent(MotionEvent ev) 0
                        onInterceptTouchEvent(MotionEvent ev) 0
 E/TouchEventView: dispatchTouchEvent(MotionEvent event) 0
 E/TouchEventView: onTouchEvent(MotionEvent event) 0
 E/TouchEventViewGroup: onTouchEvent(MotionEvent event) 0

 E/TouchEventActivity: dispatchTouchEvent(MotionEvent ev) 2
 E/TouchEventViewGroup: dispatchTouchEvent(MotionEvent ev) 2
                        onTouchEvent(MotionEvent event) 2
 E/TouchEventActivity: dispatchTouchEvent(MotionEvent ev) 2
 E/TouchEventViewGroup: dispatchTouchEvent(MotionEvent ev) 2
 E/TouchEventViewGroup: onTouchEvent(MotionEvent event) 2
 E/TouchEventActivity: dispatchTouchEvent(MotionEvent ev) 2
 E/TouchEventViewGroup: dispatchTouchEvent(MotionEvent ev) 2
                        onTouchEvent(MotionEvent event) 2
 E/TouchEventActivity: dispatchTouchEvent(MotionEvent ev) 2
 E/TouchEventViewGroup: dispatchTouchEvent(MotionEvent ev) 2
                        onTouchEvent(MotionEvent event) 2

 E/TouchEventActivity: dispatchTouchEvent(MotionEvent ev) 1
 E/TouchEventViewGroup: dispatchTouchEvent(MotionEvent ev) 1
                        onTouchEvent(MotionEvent event) 1

 * */