package cn.stu.cusview.ruiz.customeviewdemo.camera;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.DocumentsProvider;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import cn.stu.cusview.ruiz.customeviewdemo.R;
/**
 *
 * 调用系统的相册和相机
 * */
public class CameraActivity extends AppCompatActivity {

    private static final String TAG = "CameraActivity";

    private final int CAMERA = 0;
    private final int ALBUM = 1;


    private Uri imgUri;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        imageView = findViewById(R.id.imageView);
    }


    /**
     * 打开系统拍照，
     */
    public void openCamera(View view) {
        File outFile = new File(getExternalCacheDir(), "camera_pic.png");//使用了app安全空间，照片出来后的存放文件
        if (outFile.exists()) {
            outFile.delete();
        }
        try {
            outFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        imgUri = null;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {//如果大于24需要使用新的文件provider，安全策略
            imgUri = FileProvider.getUriForFile(this, "com.stu.ruiz.fileprovider", outFile);
        } else {
            imgUri = Uri.parse(outFile.getAbsolutePath());
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
        startActivityForResult(intent, CAMERA);
    }

    /**
     * 打开系统相册
     * */
    public void openAlbum(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, ALBUM);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case CAMERA:
                if (resultCode == RESULT_OK) {
                    try {
                        InputStream is = getContentResolver().openInputStream(imgUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(is);
                        imageView.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case ALBUM:
                if (resultCode == RESULT_OK) {
                    String imagePath = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        imagePath = handleImageonKttka(data);
                    } else {
                       imagePath = handleImageBeforeKitkat(data);
                    }
                    Log.e(TAG, "onActivityResult: imagePath"+imagePath);
                    InputStream is = null;
                    try {
                        is = getContentResolver().openInputStream(data.getData());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }


                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    imageView.setImageBitmap(bitmap);
                }
                break;
        }


    }

    /**
     * 高版本处理相册选择
     * */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private String handleImageBeforeKitkat(Intent data) {
        Uri uri = data.getData();
        String imagPath = null;
        if (DocumentsContract.isDocumentUri(this, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equalsIgnoreCase(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagPath = getImagePathFromResolver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equalsIgnoreCase(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(uri,Long.valueOf(docId));
                imagPath = getImagePathFromResolver(contentUri,null);
            }

        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imagPath = getImagePathFromResolver(uri,null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            imagPath = uri.getPath();
        }

        return imagPath;
    }

    /**
     * 使用provider查询图片地址
     * */
    private String getImagePathFromResolver(Uri externalContentUri, String selection) {

        String imagePath = null;
        try {
            Cursor cursor = getContentResolver().query(externalContentUri, null, selection, null, null);
            cursor.moveToFirst();
            imagePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        } catch (Exception e) {
            imagePath = null;
        }

        return imagePath;
    }

    /**
     * 4.4以前的相册图片地址获取
     * */
    private String handleImageonKttka(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        imagePath = getImagePathFromResolver(uri,null);
        return imagePath;
    }
}
