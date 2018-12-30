package cn.stu.cusview.ruiz.customeviewdemo.cameraapi;

import android.Manifest;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.TextureView;

import java.util.List;

import cn.stu.cusview.ruiz.customeviewdemo.R;
import cn.stu.cusview.ruiz.customeviewdemo.uilt.permission.PermissionUtil;

public class CameraTextureActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION = 0x0001;

    private TextureView texture_view;

    private Camera mCamera;

    private SurfaceTexture mSurfaceTexture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_texture);
        texture_view = findViewById(R.id.texture_view);
        texture_view.setSurfaceTextureListener(listener);

        PermissionUtil util = new PermissionUtil();
        List<String> needPermission = util.checkPermission(this,
                new String[]{Manifest.permission.CAMERA});
        if (needPermission!=null && needPermission.size()>0){
            util.requestPermissions(this,needPermission,REQUEST_PERMISSION);
        }

    }



    TextureView.SurfaceTextureListener listener =new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            mSurfaceTexture = surface;

            try {
                mCamera = Camera.open();
                mCamera.setPreviewTexture(surface);
                mCamera.setDisplayOrientation(90);
                mCamera.startPreview();
            }catch (Exception e){

            }
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            mCamera.release();
            mCamera = null;
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {

        }
    };
}
