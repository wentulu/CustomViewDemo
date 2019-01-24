package cn.stu.cusview.ruiz.customeviewdemo;

import android.app.Application;
import android.content.res.Configuration;

import cn.stu.cusview.ruiz.customeviewdemo.lifecallback.ActivityLifeCallBack;

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivityLifeCallBack());
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }
}
