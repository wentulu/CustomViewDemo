package cn.stu.cusview.ruiz.customeviewdemo.audiored;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;

public class AudioConfigration {

    /**
     * 频率，可参考AudioRecord Constructor的第二个参数介绍
     * */
    public static final int frequency = 4410;

    /**
     * 采样率，采样率越高保真越好， means to use a route-dependent value
     *   which is usually the sample rate of the source.
     * */
    public static final int AUDIOFORMATE = AudioFormat.ENCODING_PCM_16BIT;

    /**
     * 声音来源
     * */
    public static final int AUDIOSOURCE = MediaRecorder.AudioSource.MIC;

    /**
     * describes the configuration of the audio channels.
     * */
    public static final int AUDIOCHANNEL = AudioFormat.CHANNEL_IN_MONO;


}
