package cn.stu.cusview.ruiz.customeviewdemo.audiored;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import cn.stu.cusview.ruiz.customeviewdemo.R;
import cn.stu.cusview.ruiz.customeviewdemo.uilt.permission.PermissionUtil;

import static cn.stu.cusview.ruiz.customeviewdemo.audiored.GlobalConfig.AUDIO_FORMAT;
import static cn.stu.cusview.ruiz.customeviewdemo.audiored.GlobalConfig.CHANNEL_CONFIG;
import static cn.stu.cusview.ruiz.customeviewdemo.audiored.GlobalConfig.SAMPLE_RATE_INHZ;

public class AudioRecord2Activity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AudioRecord2Activity";

    private static final int PERMISSION_RESQUEST = 0x0001;

    private AudioRecord mAudioRecord;
    private boolean isRecording;

    private int bufferSize;
    private byte[] dataBuffer;

    private Button start_stop_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_record2);
        start_stop_btn = findViewById(R.id.start_stop_btn);
        start_stop_btn.setOnClickListener(this);

        checkPermissions();

    }

    private void checkPermissions() {
        String[] permissions = new String[]{Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        PermissionUtil util = new PermissionUtil();
        List<String> pers = util.checkPermission(this, permissions);
        if (pers != null && pers.size() > 0) {
            util.requestPermissions(this, pers, PERMISSION_RESQUEST);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_stop_btn:
                if (isRecording) {
                    stopRecord();
                } else {
                    startRecord();
                }
                break;
        }
    }


    private void startRecord() {
        bufferSize = AudioRecord.getMinBufferSize(SAMPLE_RATE_INHZ, CHANNEL_CONFIG, AUDIO_FORMAT);
        mAudioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, SAMPLE_RATE_INHZ, CHANNEL_CONFIG, AUDIO_FORMAT, bufferSize);
        dataBuffer = new byte[bufferSize];

        final File templeFile = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC), "test2.pcm");
        if (templeFile.exists()) {
            templeFile.delete();
        }
        mAudioRecord.startRecording();
        isRecording = true;
        new Thread(new Runnable() {
            @Override
            public void run() {

                FileOutputStream os = null;

                try {
                    os = new FileOutputStream(templeFile);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                if (os != null) {
                    try {
                        while (isRecording) {

                            int result = mAudioRecord.read(dataBuffer, 0, bufferSize);
                            if (result != AudioRecord.ERROR_INVALID_OPERATION) {
                                os.write(dataBuffer);
                            }

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        stopRecord();
                    }finally {
                        try {
                            os.flush();
                            os.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                new PcmtoWavUitl(SAMPLE_RATE_INHZ,CHANNEL_CONFIG,AUDIO_FORMAT)
                        .pcmToWav(templeFile.getAbsolutePath(),
                                new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC),"test.wav").getAbsolutePath());


            }
        }).start();
    }


    private void stopRecord() {
        mAudioRecord.stop();
        isRecording = false;
        mAudioRecord.release();
        mAudioRecord = null;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_RESQUEST:
                if (grantResults != null) {
                    boolean isGranted = true;
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            isGranted = false;
                            break;
                        }
                    }
                    if (isGranted) {

                    } else {
                        Toast.makeText(this, "Permissions granted successful!", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
        }
    }
}
