package cn.stu.cusview.ruiz.customeviewdemo.jnitest;

public class JniDynammicUtil {
    static {
        System.loadLibrary("Jni_Dynamic_Test");
    }

    public static native String getStringD();

}
