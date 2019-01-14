package cn.stu.cusview.ruiz.customeviewdemo.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cn.stu.cusview.ruiz.customeviewdemo.MainActivity;
import cn.stu.cusview.ruiz.customeviewdemo.R;

public class NotificationActivity extends AppCompatActivity {


    NotificationManager mNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("cu.stu.ruiz", "push", NotificationManager.IMPORTANCE_HIGH);
//            channel.enableLights(true);
//            channel.setLightColor(Color.RED);
//            channel.setLightColor(ContextCompat.getColor(this, R.color.colorAccent));
//            channel.setShowBadge(true);
//            channel.setDescription("互救吧");
//            channel.enableVibration(true);//及时设置了也会由于手机存在缓存的原因而失效
//            channel.setVibrationPattern(new long[]{0,300,300,300,300,300});
            if (mNotificationManager != null) {
                mNotificationManager.createNotificationChannel(channel);
            }
        }

    }


    public void sendNotification(View view) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"cu.stu.ruiz");
        Notification notification = builder.setContentTitle("Notification Demo")
                .setContentText("AK kdknjf kdfhaskhfd kdsfhaksjd sdkfhaskhdfka sakdfhask")
                .setSmallIcon(R.drawable.ic_launcher)//这张图片要特别注意
                .setWhen(System.currentTimeMillis())
                .setTicker("6788")
                .setAutoCancel(true)
//                .setLights(Color.RED,300,300)
                .setTicker("message")
//                .setVibrate(new long[]{0,2000,1000,1000,1000,1000})
                .build();

        mNotificationManager.notify(1,notification);



    }


}
