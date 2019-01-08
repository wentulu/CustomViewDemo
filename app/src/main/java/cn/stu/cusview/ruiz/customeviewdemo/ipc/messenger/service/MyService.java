package cn.stu.cusview.ruiz.customeviewdemo.ipc.messenger.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import cn.stu.cusview.ruiz.customeviewdemo.ipc.messenger.MessengerArg;

public class MyService extends Service {
    private static final String TAG = "MyService";

    private final boolean DEBUG = true;


    private final Messenger mMessenger = new Messenger(new MessengerHandler());

    private static Messenger mclientMessenger;
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    /**
     * 消息处理的handler
     * */
    private static class MessengerHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MessengerArg.GREETING_FROM_CLIENT:
                    Log.e(TAG,"Greeting from client.");
                    Messenger client = msg.replyTo;
                    mclientMessenger = client;
                    Bundle bundle = new Bundle();
                    bundle.putString("DATA","Hello, Client!");
                    Message message = Message.obtain(null,MessengerArg.GREETING_FROM_SERVICE);
                    message.setData(bundle);
                    try {
                        client.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Bundle bundle = new Bundle();
                    bundle.putString("DATA","Hello, Client! Im greeting to you.");
                    Message message = Message.obtain(null,MessengerArg.GREETING_FROM_SERVICE);
                    message.setData(bundle);
                    try {
                        mclientMessenger.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            },30000l);

        }
    }

}
