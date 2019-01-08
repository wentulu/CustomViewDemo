package cn.stu.cusview.ruiz.customeviewdemo.ipc.messenger.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import cn.stu.cusview.ruiz.customeviewdemo.R;
import cn.stu.cusview.ruiz.customeviewdemo.ipc.messenger.MessengerArg;
import cn.stu.cusview.ruiz.customeviewdemo.ipc.messenger.service.MyService;

public class MessengerClientActivity extends AppCompatActivity {

    private static final String TAG = "MessengerClientActivity";


    private final boolean DEBUG = true;

    private Messenger mMessenger;

    private final Messenger reciverMessenger = new Messenger(new MessengerHandler());

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMessenger = new Messenger(service);
            Message msg = Message.obtain(null,MessengerArg.GREETING_FROM_CLIENT);
            //Message的object目前只有系统的实现了parcelable接口的才能传输。
            Bundle bundle = new Bundle();
            bundle.putString("DATA","Hello Service!");
            msg.replyTo = reciverMessenger;
            msg.setData(bundle);

            try {
                mMessenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger_client);
        Intent i = new Intent(this, MyService.class);
        bindService(i,mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
    }



    static class MessengerHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MessengerArg.GREETING_FROM_SERVICE:
                    Log.e(TAG,"Greeting From Service");
                    Log.e(TAG,"info"+msg.getData().getString("DATA"));
                    break;

                default:
                super.handleMessage(msg);
            }
        }
    }
}
