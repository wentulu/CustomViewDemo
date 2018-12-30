package cn.stu.cusview.ruiz.customeviewdemo.audiored;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.stu.cusview.ruiz.customeviewdemo.R;

import static cn.stu.cusview.ruiz.customeviewdemo.audiored.GlobalConfig.AUDIO_FORMAT;
import static cn.stu.cusview.ruiz.customeviewdemo.audiored.GlobalConfig.CHANNEL_CONFIG;
import static cn.stu.cusview.ruiz.customeviewdemo.audiored.GlobalConfig.SAMPLE_RATE_INHZ;

public class AudioRecordActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_PERMISSIONS = 0X0001;

    private Button start_stop_btn;

    private boolean isRecording;

    private AudioRecord mAudioRecord,audioRecord;

    private int bufferSize;
    private byte[] dataBuffer;

    private File templeFile;
//    = new File(getExternalFilesDir("music"),"record1.pcm");//在这里会失败，调用了实例方法

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_record);

        checkPermissionList();

        initView();
//        initAudio();
    }

    private void checkPermissionList() {
        String[] permissions = new String[]{
                Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        List<String> needRequestPermission = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {

            } else {
                needRequestPermission.add(permission);
            }
        }
        String[] needRequests = new String[needRequestPermission.size()];
        needRequestPermission.toArray(needRequests);
        if (needRequestPermission.size() > 0)//保证每次申请的权限最少一个，数量为0会illegal exception
            ActivityCompat.requestPermissions(this, needRequests, REQUEST_PERMISSIONS);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    private void initAudio() {
        bufferSize = AudioRecord.getMinBufferSize(AudioConfigration.frequency,
                AudioConfigration.AUDIOCHANNEL, AudioConfigration.AUDIOFORMATE);
        mAudioRecord = new AudioRecord(AudioConfigration.AUDIOSOURCE,
                AudioConfigration.frequency, AudioConfigration.AUDIOCHANNEL,
                AudioConfigration.AUDIOFORMATE, bufferSize);
        dataBuffer = new byte[bufferSize];
    }

    private void initView() {
        start_stop_btn = findViewById(R.id.start_stop_btn);
        start_stop_btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_stop_btn:
                if (!isRecording) {
                    startRecord();
                } else {
                    stopRecord();
                }

                break;
        }
    }
    public void startRecord() {
        final int minBufferSize = AudioRecord.getMinBufferSize(SAMPLE_RATE_INHZ, CHANNEL_CONFIG, AUDIO_FORMAT);
        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, SAMPLE_RATE_INHZ,
                CHANNEL_CONFIG, AUDIO_FORMAT, minBufferSize);

        final byte data[] = new byte[minBufferSize];
        final File file = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC), "test.pcm");
        if (!file.mkdirs()) {
            Log.e("audio", "Directory not created");
        }
        if (file.exists()) {
            file.delete();
        }

        audioRecord.startRecording();
        isRecording = true;

        // TODO: 2018/3/10 pcm数据无法直接播放，保存为WAV格式。

        new Thread(new Runnable() {
            @Override
            public void run() {

                FileOutputStream os = null;
                try {
                    os = new FileOutputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                if (null != os) {
                    while (isRecording) {
                        int read = audioRecord.read(data, 0, minBufferSize);
                        // 如果读取音频数据没有出现错误，就将数据写入到文件
                        if (AudioRecord.ERROR_INVALID_OPERATION != read) {
                            try {
                                os.write(data);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    new PcmtoWavUitl(SAMPLE_RATE_INHZ,CHANNEL_CONFIG,AUDIO_FORMAT)
                            .pcmToWav(file.getAbsolutePath(),
                                    new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC),"test.wav").getAbsolutePath());

                    try {
                        Log.i("audio", "run: close file output stream !");
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    public void stopRecord() {
        isRecording = false;
        // 释放资源
        if (null != audioRecord) {
            audioRecord.stop();
            audioRecord.release();
            audioRecord = null;
            //recordingThread = null;
        }
    }

    private void saveData() {
        templeFile = new File(getExternalFilesDir("music"), "record1.pcm");
        Log.e("File location",templeFile.getAbsolutePath());
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (templeFile.isHidden()) {
                    templeFile.delete();
                } else {
                    try {
                        templeFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                FileOutputStream os = null;
                try {
                    os = new FileOutputStream(templeFile);

                    mAudioRecord.startRecording();
                    isRecording = true;
                    while (isRecording) {
                        int result = mAudioRecord.read(dataBuffer, 0, bufferSize);
                        if (result != AudioRecord.ERROR_INVALID_OPERATION) {
                            os.write(dataBuffer);
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        os.flush();
                        os.close();
                        os = null;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                new PcmtoWavUitl(AudioConfigration.frequency,AudioConfigration.AUDIOCHANNEL,AudioConfigration.AUDIOFORMATE)
                .pcmToWav(templeFile.getAbsolutePath(),new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC),"Record.wav").getAbsolutePath());

            }
        }).start();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mAudioRecord.release();
//        mAudioRecord = null;
    }
}
