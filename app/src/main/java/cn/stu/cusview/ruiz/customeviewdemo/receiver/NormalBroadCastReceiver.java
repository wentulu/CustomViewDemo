package cn.stu.cusview.ruiz.customeviewdemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


/**
 * Receiver 分为全局广播和本地广播两种，从接收者的范围上来看
 * 1.全局的注册方式有两种
 *      1).AndroidManiFest.xml注册，静态注册
 *      2).在指定的地方使用registerReceiver注册，动态注册
 * 2.本地的广播只能动态注册
 *      借助于LocalReceiverManager来发送广播和注册广播
 *
 * */

public class NormalBroadCastReceiver extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {

    }
}
