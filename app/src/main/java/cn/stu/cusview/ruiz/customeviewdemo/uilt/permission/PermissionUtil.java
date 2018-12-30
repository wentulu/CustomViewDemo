package cn.stu.cusview.ruiz.customeviewdemo.uilt.permission;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限检查与申请
 * */
public class PermissionUtil {


    /**
     * 检查需要的权限是否已经被授予
     * @param context 上下文对象，
     * @param permissions String[] 需要使用的权限
     * @return List<String> 返回没有授权的权限集合
     *
     * */
    public List<String> checkPermission(Context context, String[] permissions) {
        List<String> noGrantedPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                noGrantedPermissions.add(permission);
            }
        }
        return noGrantedPermissions.size()>0? noGrantedPermissions : null;
    }

    /**
     * 申请权限
     * @param activity
     * @param permissions 申请的权限
     * @param requestCode 请求码，用于拿到权限授权的结果
     * */
    public void requestPermissions(@NonNull AppCompatActivity activity, @NonNull List<String> permissions, int requestCode){
        if (permissions!=null && permissions.size()>0){
            String[] needRequestPermissions = new String[permissions.size()];
            permissions.toArray(needRequestPermissions);
            ActivityCompat.requestPermissions(activity,needRequestPermissions,requestCode);
        }else {

        }
    }


}
