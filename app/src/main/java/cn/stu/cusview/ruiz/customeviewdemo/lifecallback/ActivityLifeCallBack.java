package cn.stu.cusview.ruiz.customeviewdemo.lifecallback;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import java.lang.ref.WeakReference;

public class ActivityLifeCallBack  implements Application.ActivityLifecycleCallbacks{

    private static final String TAG = "ActivityLifeCallBack";
    private final boolean DEBUG = false;

//    WeakReference<Activity> mActivityWeakReference = ;

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (DEBUG){
            Log.e(TAG,activity.getClass().getSimpleName()+"--->"+"onActivityCreated()");

        }
    }

    @Override
    public void onActivityStarted(Activity activity) {
        if (DEBUG){
            Log.e(TAG,activity.getClass().getSimpleName()+"--->"+"onActivityStarted()");

        }
    }



    @Override
    public void onActivityResumed(Activity activity) {
        if (DEBUG){
            Log.e(TAG,activity.getClass().getSimpleName()+"--->"+"onActivityResumed()");

        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        if (DEBUG){
            Log.e(TAG,activity.getClass().getSimpleName()+"--->"+"onActivityPaused()");

        }

    }

    @Override
    public void onActivityStopped(Activity activity) {
        if (DEBUG){
            Log.e(TAG,activity.getClass().getSimpleName()+"--->"+"onActivityStopped()");

        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        if (DEBUG){
            Log.e(TAG,activity.getClass().getSimpleName()+"--->"+"onActivitySaveInstanceState()");

        }
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        if (DEBUG){
            Log.e(TAG,activity.getClass().getSimpleName()+"--->"+"onActivityDestroyed()");

        }
    }

}
