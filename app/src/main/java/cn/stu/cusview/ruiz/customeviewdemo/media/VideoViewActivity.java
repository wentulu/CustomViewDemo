package cn.stu.cusview.ruiz.customeviewdemo.media;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.VideoView;

import java.io.File;

import cn.stu.cusview.ruiz.customeviewdemo.R;

public class VideoViewActivity extends AppCompatActivity {

    private static final String TAG = "VideoViewActivity";

    private VideoView video_view;

    private File mp4File;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        video_view = findViewById(R.id.video_view);

        mp4File = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"nba.mp4");
        Log.e(TAG,mp4File.getAbsolutePath());
        if (mp4File.exists()){
            Log.e(TAG,"MP3文件文在");
        }else {
            Log.e(TAG,"MP3文件文不存在");
            return;
        }

        video_view.setVideoPath(mp4File.getAbsolutePath());
        video_view.start();
    }


    public void onPlay(View view){
        video_view.start();
    }


    public void onStop(View view){
        video_view.stopPlayback();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        video_view.suspend();
    }
}
