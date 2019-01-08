package cn.stu.cusview.ruiz.customeviewdemo.ipc.sharedfile;

import android.content.SharedPreferences;

/**
 * 共享一个文件来传递数据
 *
 *
 *
 * 注意SharedPreferences是经过系统优化会有多进程访问的问题 ，特别是本身自带的缓存机制
 * */

public class SharedFile {
    SharedPreferences f;
}
