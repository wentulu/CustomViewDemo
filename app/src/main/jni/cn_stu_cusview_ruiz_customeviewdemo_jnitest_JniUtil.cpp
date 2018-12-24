
#include "cn_stu_cusview_ruiz_customeviewdemo_jnitest_JniUtil.h"
/*
 * Class:     cn_stu_cusview_ruiz_customeviewdemo_jnitest_JniUtil
 * Method:    getString
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL
Java_cn_stu_cusview_ruiz_customeviewdemo_jnitest_JniUtil_getString(JNIEnv *env, jobject)
  {

    return env-> NewStringUTF("Hello World!");
  }


