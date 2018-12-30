package cn.stu.cusview.ruiz.customeviewdemo.cameraapi;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.CameraManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.security.Permission;
import java.util.List;

import cn.stu.cusview.ruiz.customeviewdemo.R;
import cn.stu.cusview.ruiz.customeviewdemo.uilt.permission.PermissionUtil;

public class CameraApiActivity extends AppCompatActivity {


    private static final int REQUEST_PERMISSION = 0x0001;

    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;

    private Camera mCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_api);
        mSurfaceView = findViewById(R.id.surface_view);
        mSurfaceView.getHolder().addCallback(mCallback);

        PermissionUtil util = new PermissionUtil();
        List<String> needPermission = util.checkPermission(this,
                new String[]{Manifest.permission.CAMERA});
        if (needPermission!=null && needPermission.size()>0){
            util.requestPermissions(this,needPermission,REQUEST_PERMISSION);
        }

    }



    SurfaceHolder.Callback mCallback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {

            mSurfaceHolder = holder;

            try {
                mCamera = Camera.open();
                mCamera.setPreviewDisplay(holder);
                mCamera.setDisplayOrientation(90);
                mCamera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            mCamera.release();
            mCamera = null;
        }
    };


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
            try {
                if (mCamera!=null){
                    mCamera.stopPreview();
                    mCamera.setPreviewDisplay(null);
                    mCamera.release();
                    mCamera =null;
                }
                mCamera = Camera.open();
                mCamera.setPreviewDisplay(mSurfaceHolder);
                mCamera.setDisplayOrientation(90);
                mCamera.startPreview();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
