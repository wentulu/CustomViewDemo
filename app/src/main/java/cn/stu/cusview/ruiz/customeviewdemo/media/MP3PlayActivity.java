package cn.stu.cusview.ruiz.customeviewdemo.media;

import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.IOException;

import cn.stu.cusview.ruiz.customeviewdemo.R;

public class MP3PlayActivity extends AppCompatActivity {

    private static final String TAG = "MP3PlayActivity";
    private static final boolean DEBUG = true;


    private MediaPlayer mMediaPlayer;

    private int duration;
    File mp3File = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp3_play);
        mp3File = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"We Can't Stop.mp3");
        Log.e(TAG,mp3File.getAbsolutePath());
        if (mp3File.exists()){
            Log.e(TAG,"MP3文件文在");
        }else {
            Log.e(TAG,"MP3文件文不存在");
            return;
        }
        try {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource(mp3File.getAbsolutePath());
            mMediaPlayer.prepare();
            mMediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
                @Override
                public void onSeekComplete(MediaPlayer mp) {
                   int time = mp.getCurrentPosition();
                   Log.e(TAG,"Time:"+time);
                }
            });
        } catch (IOException e) {
            Log.e(TAG,"Media Player Init Failed.");
        }
    }


    public void onPlay(View view){
        if (mMediaPlayer!=null){

            if (mMediaPlayer.isPlaying()){

            }else {
                duration = mMediaPlayer.getDuration();
                Log.e(TAG,"duration:"+duration);
                mMediaPlayer.start();
            }
        }else {
            Log.e(TAG,"Make sure Media Player has been init.");
        }
    }


    public void onPause(View view){
        if (mMediaPlayer!=null){

            if (mMediaPlayer.isPlaying()){
                mMediaPlayer.pause();
            }else {
                duration = mMediaPlayer.getDuration();
                mMediaPlayer.start();
            }
        }else {
            Log.e(TAG,"Make sure Media Player has been init.");
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer!=null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
        }
    }
}
