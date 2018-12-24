LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := jni_test
LOCAL_SRC_FILES := cn_stu_cusview_ruiz_customeviewdemo_jnitest_JniUtil.cpp
include $(BUILD_SHARED_LIBRARY)


include $(CLEAR_VARS)
LOCAL_MODULE := Jni_Dynamic_Test
LOCAL_SRC_FILES := jnidynamicutil.cpp
include $(BUILD_SHARED_LIBRARY)