package cn.stu.cusview.ruiz.customeviewdemo.fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import cn.stu.cusview.ruiz.customeviewdemo.R;

public class FragmentDeActivity extends AppCompatActivity {

    private static final String TAG = "FragmentDeActivity";

    private boolean DEBUG = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_de);
        if (DEBUG){
            Log.e(TAG,"onCreate()");
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG,"onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"onResume()");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"onStop()");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy()");
    }
}
