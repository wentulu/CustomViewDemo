package cn.stu.cusview.ruiz.customeviewdemo;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import cn.stu.cusview.ruiz.customeviewdemo.audiored.AudioRecord2Activity;
import cn.stu.cusview.ruiz.customeviewdemo.audiored.AudioRecordActivity;
import cn.stu.cusview.ruiz.customeviewdemo.camera.AlbumActivityActivity;
import cn.stu.cusview.ruiz.customeviewdemo.camera.CameraActivity;
import cn.stu.cusview.ruiz.customeviewdemo.cameraapi.CameraApiActivity;
import cn.stu.cusview.ruiz.customeviewdemo.cameraapi.CameraTextureActivity;
import cn.stu.cusview.ruiz.customeviewdemo.db.BookMemoryActivity;
import cn.stu.cusview.ruiz.customeviewdemo.dialog.AlertDialogActivity;
import cn.stu.cusview.ruiz.customeviewdemo.file.FileActivity;
import cn.stu.cusview.ruiz.customeviewdemo.fragment.FragmentDeActivity;
import cn.stu.cusview.ruiz.customeviewdemo.installedapp.InsatlledAppActivity;
import cn.stu.cusview.ruiz.customeviewdemo.ipc.messenger.client.MessengerClientActivity;
import cn.stu.cusview.ruiz.customeviewdemo.jnitest.JniDynammicUtil;
import cn.stu.cusview.ruiz.customeviewdemo.jnitest.JniUtil;
import cn.stu.cusview.ruiz.customeviewdemo.notification.NotificationActivity;
import cn.stu.cusview.ruiz.customeviewdemo.provider.ContactsActivity;
import cn.stu.cusview.ruiz.customeviewdemo.socket.ServerSocketService;
import cn.stu.cusview.ruiz.customeviewdemo.socket.SocketClientActivity;
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
 * <p>
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

        Intent intent = new Intent(this, ServerSocketService.class);
        startService(intent);

    }


    @Override
    protected void onResume() {
        if (DEBUG) {
            Log.e(TAG, "onResume()");
        }
        super.onResume();
        TextView text_jni = findViewById(R.id.jni_tv);
        text_jni.setText(JniDynammicUtil.getStringD()+new JniUtil().getString());
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
        Intent intent = new Intent(this, ServerSocketService.class);
        stopService(intent);
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

       Log.e(TAG,"键盘状态"+newConfig.keyboardHidden);
       Log.e(TAG,"");
    }

    public void goTitleView(View view) {
        Intent titleViewIntent = new Intent(this, TitleViewActivity.class);
        startActivity(titleViewIntent);
    }

    public void goTouchView(View view){
        Intent touchViewIntent = new Intent(this, TouchEventActivity.class);
        startActivity(touchViewIntent);
    }

    public void openCameraAlbum(View view){
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }


    public void openGetInstallApp(View view){
        Intent in  = new Intent();
        in.setAction(Intent.ACTION_VIEW);
        in.addCategory(Intent.CATEGORY_BROWSABLE);
        in.setData(Uri.parse("http://m.dhgate.com/product"));
        startActivity(in);
    }


    public void getConfiguration() {
        Configuration configuration = getResources().getConfiguration();
        Log.e(TAG, configuration.mcc + "");
        Log.e(TAG, configuration.mnc + "");
        if (Build.VERSION_CODES.N <= Build.VERSION.SDK_INT)
            Log.e(TAG,configuration.getLocales().get(0).getISO3Country());
        Log.e(TAG,"orientation"+configuration.orientation+"");
    }


    public void openAudioRecord(View view){
        Intent intent = new Intent(this, AudioRecord2Activity.class);
        startActivity(intent);
    }


    public void goCameraApi(View view){
        Intent intent = new Intent(this, CameraApiActivity.class);
        startActivity(intent);
    }


    public void goCameraApiTexture(View view){
        Intent intent = new Intent(this, CameraTextureActivity.class);
        startActivity(intent);
    }


    public void openServiceActivity(View view){
        Intent intent = new Intent(this, MessengerClientActivity.class);
        startActivity(intent);
    }


    public void goDialog(View view){
        Intent intent = new Intent(this, AlertDialogActivity.class);
        startActivity(intent);
    }

    public void goFragmentLifeCycle(View view){
        Intent intent = new Intent(this, FragmentDeActivity.class);
        startActivity(intent);
    }


    public void goFileSave(View view){
        Intent intent = new Intent(this, FileActivity.class);
        startActivity(intent);
    }


    public void goDataBaseAct(View view){
        Intent intent = new Intent(this, BookMemoryActivity.class);
        startActivity(intent);
    }


    public void goContacts(View view){
        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
    }


    public void goNotification(View view){
        Intent intent = new Intent(this, NotificationActivity.class);
        startActivity(intent);
    }


    public void goSocketClient(View view){
        Intent intent = new Intent(this, SocketClientActivity.class);
        startActivity(intent);
    }


    public void goCameraAlbum(View view){
        Intent intent = new Intent(this, AlbumActivityActivity.class);
        startActivity(intent);
    }

}
