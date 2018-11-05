package cn.stu.cusview.ruiz.customeviewdemo;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import cn.stu.cusview.ruiz.customeviewdemo.view.TouchEventView;


/**
 * 1、Activity的启动日志，即切换Activity的生命周期活动,切换后再回来不会执行onRestoreInstanceState(Bundle savedInstanceState)
 * /MainActivity: onCreate(Bundle savedInstanceState)
 * /MainActivity: onStart()
 * /MainActivity: onResume()
 * /MainActivity: onPause()
 * /TitleViewActivity: onCreate(Bundle savedInstanceState)
 * /TitleViewActivity: onStart()
 * /TitleViewActivity: onResume()
 * /MainActivity: onSaveInstanceState(Bundle outState)
 * /MainActivity: onStop()
 * /TitleViewActivity: finish()
 * /TitleViewActivity: onPause()
 * /MainActivity: onRestart()
 * /MainActivity: onStart()
 * /MainActivity: onResume()
 * /TitleViewActivity: onStop()
 * /TitleViewActivity: onDestroy()
 * <p>
 * 2、Activity旋转后的日志 并未关闭生命周期执行的
 * /MainActivity: onPause()
 * /MainActivity: onSaveInstanceState(Bundle outState)
 * /MainActivity: onStop()
 * /MainActivity: onDestroy()
 * /MainActivity: onCreate(Bundle savedInstanceState)
 * /MainActivity: onStart()
 * /MainActivity: onRestoreInstanceState(Bundle savedInstanceState)
 * restore String Title=add //输出已保存信息
 * /MainActivity: onResume()
 * <p>
 * 3、强制不执行生命周期方法只会调用onConfigurationChanged(Configuration newConfig)
 * /MainActivity: onCreate(Bundle savedInstanceState)
 * /MainActivity: onStart()
 * /MainActivity: onResume()
 * /MainActivity: onConfigurationChanged(Configuration newConfig)
 * /MainActivity: onConfigurationChanged(Configuration newConfig)
 * /MainActivity: onConfigurationChanged(Configuration newConfig)
 * /MainActivity: onConfigurationChanged(Configuration newConfig)
 * /MainActivity: onConfigurationChanged(Configuration newConfig)
 * <p>
 * 1、onSaveInstanceState()在onPause()和OnStop()之间执行,如果涉及到Activity切换则在
 * 下一个Activity的onResume()之后
 * <p>
 * 2、restoreInstanceState()在onStart()和onResume()之间执行，参考上方日志猜测只会在这两个生命周期方法内
 * <p>
 * 3、启动Activity事，上一个Activity onPause()之后下一个Activity的生命周期会执行到onResume()
 * 接着才是上一个Activity的onStop()
 * <p>
 * 4 退出一个Activity时，当前Activity先走finish()启动销毁，紧接着是onPause()
 * 然后上一个Activity的onRestart()直到onResume(),当前Activity才会走onStop()到销毁
 */

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();
    private static final boolean DEBUG = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (DEBUG) {
            Log.e(TAG, "onCreate(Bundle savedInstanceState)");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onRestart() {
        if (DEBUG) {
            Log.e(TAG, "onRestart()");
        }
        super.onRestart();
    }

    @Override
    protected void onStart() {
        if (DEBUG) {
            Log.e(TAG, "onStart()");
        }
        super.onStart();
    }


    @Override
    protected void onResume() {
        if (DEBUG) {
            Log.e(TAG, "onResume()");
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (DEBUG) {
            Log.e(TAG, "onPause()");
        }
        super.onPause();
    }


    @Override
    protected void onStop() {
        if (DEBUG) {
            Log.e(TAG, "onStop()");
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (DEBUG) {
            Log.e(TAG, "onDestroy()");
        }
        super.onDestroy();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (DEBUG) {
            Log.e(TAG, "onSaveInstanceState(Bundle outState)");
        }
        super.onSaveInstanceState(outState);
        outState.putString("Title", "add");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (DEBUG) {
            Log.e(TAG, "onRestoreInstanceState(Bundle savedInstanceState)");
        }
        super.onRestoreInstanceState(savedInstanceState);
        if (DEBUG) {
            Log.e(TAG, "restore String Title=" + savedInstanceState.getString("Title"));
        }
    }

    @Override
    public void finish() {
        if (DEBUG) {
            Log.e(TAG, "finish()");
        }
        super.finish();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (DEBUG)
            Log.e(TAG, "onConfigurationChanged(Configuration newConfig)");
        super.onConfigurationChanged(newConfig);

    }

    public void goTitleView(View view) {
        Intent titleViewIntent = new Intent(this, TitleViewActivity.class);
        startActivity(titleViewIntent);
    }

    public void goTouchView(View view){
        Intent touchViewIntent = new Intent(this, TouchEventActivity.class);
        startActivity(touchViewIntent);
    }


    public void getConfiguration() {
        Configuration configuration = getResources().getConfiguration();
        Log.e(TAG, configuration.mcc + "");
        Log.e(TAG, configuration.mnc + "");
        if (Build.VERSION_CODES.N <= Build.VERSION.SDK_INT)
            Log.e(TAG,configuration.getLocales().get(0).getISO3Country());
        Log.e(TAG,"orientation"+configuration.orientation+"");
    }
}
