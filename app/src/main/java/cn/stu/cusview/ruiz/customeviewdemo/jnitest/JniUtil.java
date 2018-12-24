package cn.stu.cusview.ruiz.customeviewdemo.jnitest;

public class JniUtil {

    static {
        System.loadLibrary("jni_test");
    }

    public native String getString();

}
