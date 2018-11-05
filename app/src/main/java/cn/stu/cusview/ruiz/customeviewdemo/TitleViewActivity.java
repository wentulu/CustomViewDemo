package cn.stu.cusview.ruiz.customeviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class TitleViewActivity extends AppCompatActivity {

    private static final String TAG = TitleViewActivity.class.getSimpleName();
    private static final boolean DEBUG = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (DEBUG){
            Log.e(TAG,"onCreate(Bundle savedInstanceState)");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_view);
    }

    @Override
    protected void onRestart() {
        if (DEBUG){
            Log.e(TAG,"onRestart()");
        }
        super.onRestart();
    }

    @Override
    protected void onStart() {
        if (DEBUG){
            Log.e(TAG,"onStart()");
        }
        super.onStart();
    }


    @Override
    protected void onResume() {
        if (DEBUG){
            Log.e(TAG,"onResume()");
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (DEBUG){
            Log.e(TAG,"onPause()");
        }
        super.onPause();
    }


    @Override
    protected void onStop() {
        if (DEBUG){
            Log.e(TAG,"onStop()");
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (DEBUG){
            Log.e(TAG,"onDestroy()");
        }
        super.onDestroy();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (DEBUG){
            Log.e(TAG,"onSaveInstanceState(Bundle outState)");
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (DEBUG){
            Log.e(TAG,"onRestoreInstanceState(Bundle savedInstanceState)");
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void finish() {
        if (DEBUG){
            Log.e(TAG,"finish()");
        }
        super.finish();
    }
}
