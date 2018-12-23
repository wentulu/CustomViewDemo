package cn.stu.cusview.ruiz.customeviewdemo;

import android.app.Application;

import cn.stu.cusview.ruiz.customeviewdemo.lifecallback.ActivityLifeCallBack;

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivityLifeCallBack());
    }
}
