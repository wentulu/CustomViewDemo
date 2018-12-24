//
// Created by Ruiz on 2018/12/24.
//

#include <stdio.h>
#include <jni.h>
#include <stdlib.h>
#include <assert.h>
#include <string.h>

jstring native_hello(JNIEnv *env, jobject clz){

    return (env)->NewStringUTF("Hello java, this is from jni---dynamic.");

}
/**
* 方法对应表
*/
static JNINativeMethod gMethods[] = {
    {"getStringD", "()Ljava/lang/String;", (void*)native_hello},//绑定
};

/*
* 为某一个类注册本地方法
*/
static int registerNativeMethods(JNIEnv* env
        , const char* className
        , JNINativeMethod* gMethods, int numMethods) {
    jclass clazz;
    clazz = (env)->FindClass(className);
    if (clazz == NULL) {
        return JNI_FALSE;
    }
    if ((env)->RegisterNatives( clazz, gMethods, numMethods) < 0) {
        return JNI_FALSE;
    }

    return JNI_TRUE;
}


/*
* 为所有类注册本地方法
*/
static int registerNatives(JNIEnv* env) {
    const char* kClassName = "cn/stu/cusview/ruiz/customeviewdemo/jnitest/JniDynammicUtil";//指定要注册的类
    return registerNativeMethods(env, kClassName, gMethods,
            sizeof(gMethods) / sizeof(gMethods[0]));
}

/*
* System.loadLibrary("lib")时调用
* 如果成功返回JNI版本, 失败返回-1
*/
JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM* vm, void* reserved) {
    JNIEnv* env = NULL;
    jint result = -1;

    if ((vm)->GetEnv((void**) &env, JNI_VERSION_1_6) != JNI_OK) {
        return -1;
    }
    assert(env != NULL);

    if (!registerNatives(env)) {//注册
        return -1;
    }
    //成功
    result = JNI_VERSION_1_6;

    return result;
}


