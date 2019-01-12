package cn.stu.cusview.ruiz.customeviewdemo.receiver;

import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.stu.cusview.ruiz.customeviewdemo.R;

public class ReceiverDemoActivity extends AppCompatActivity {


    private LocalBroadcastManager mLocalBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_demo);
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);


//        mLocalBroadcastManager.registerReceiver();

//        mLocalBroadcastManager.sendBroadcast();
//        mLocalBroadcastManager.sendBroadcastSync();
    }
}
