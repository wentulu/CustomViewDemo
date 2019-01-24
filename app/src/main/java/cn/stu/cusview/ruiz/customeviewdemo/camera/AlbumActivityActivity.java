package cn.stu.cusview.ruiz.customeviewdemo.camera;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import cn.stu.cusview.ruiz.customeviewdemo.R;
import cn.stu.cusview.ruiz.customeviewdemo.uilt.permission.PermissionUtil;

public class AlbumActivityActivity extends AppCompatActivity {

    private static final String TAG = "AlbumActivityActivity";

    private final static int CAMERA = 0x0001;
    private final static int ALBUM = 0x0002;

    private final static int CAMERA_PERMISSION = 0x0001;

    private ImageView pic_iv;

    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_activity);
        pic_iv = findViewById(R.id.pic_iv);


        PermissionUtil permissionUtil = new PermissionUtil();
        List<String> permissions = permissionUtil.checkPermission(this, new String[]{Manifest.permission.CAMERA});
        if (permissions != null && permissions.size() > 0) {
            permissionUtil.requestPermissions(this, permissions, CAMERA_PERMISSION);
        }
    }

    File picFile;
    public void goCamera(View view) {
        File pictures = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//        File pictures = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        picFile = new File(pictures.getAbsolutePath(),"pic.jpg");
        Log.e(TAG,"path="+picFile.getAbsolutePath());
        if (picFile.exists()){
            picFile.delete();
        }
        try {
            picFile.createNewFile();
        } catch (IOException e) {
            Log.e(TAG,"output file create failed!");
        }

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
             uri = FileProvider.getUriForFile(this,"com.stu.ruiz.fileprovider",picFile);
        }else {
            uri = Uri.parse(picFile.getAbsolutePath());
        }
        Log.e(TAG,"URI = "+uri);
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        startActivityForResult(intent, CAMERA);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    goCamera(null);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode!=RESULT_OK){
            return;
        }
        Bitmap bitmap = null;
        switch (requestCode) {
            case CAMERA:


                bitmap = BitmapFactory.decodeFile(picFile.getAbsolutePath());

                pic_iv.setImageBitmap(bitmap);
                break;
            case ALBUM:
                Uri uri = data.getData();
                try {
                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                pic_iv.setImageBitmap(bitmap);
                break;
        }
    }

    public void goAlbum(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,ALBUM);
    }

}
