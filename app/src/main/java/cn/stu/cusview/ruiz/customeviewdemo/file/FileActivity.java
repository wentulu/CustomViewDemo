package cn.stu.cusview.ruiz.customeviewdemo.file;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;

import cn.stu.cusview.ruiz.customeviewdemo.R;

/**
 * 有关于文件保存的信息，没有使用线程，应该在线程中操作。
 * 此处信息较少，所以没做，应该使用线程，否则会出现ANR。
 * */
public class FileActivity extends AppCompatActivity {

    private static final String TAG = "FileActivity";
    private static final boolean DEBUG = true;


    private EditText mEditText;
    private TextView info_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        mEditText = findViewById(R.id.text_edt);
        info_tv = findViewById(R.id.info_tv);
    }


    public void save(View view) {
        FileOutputStream ops = null;
        try {
            ops = openFileOutput("info.txt", MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            if (DEBUG)
                Log.e(TAG, "No file", e);
            return;
        }
        BufferedWriter bw = null;

        try {
            String info = mEditText.getText().toString().trim();

            bw = new BufferedWriter(new OutputStreamWriter(ops));
            if (TextUtils.isEmpty(info)) {
                bw.close();
            } else {
                bw.write(info, 0, info.length());
            }
        } catch (IOException e) {
            if (DEBUG) {
                Log.e(TAG, "IOExCeption", e);
            }
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    public void readInfo(View view) {
        FileInputStream fis = null;
        try {
            fis = openFileInput("info.txt");
        } catch (FileNotFoundException e) {
            if (DEBUG)
                Log.e(TAG, "No File", e);
            return;
        }
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        try {
            String line = null;
            while ((line = br.readLine())!=null){
                sb.append(line);
            }
        } catch (IOException e) {
            Log.e(TAG,"Read IoExveption",e);
        }finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            info_tv.setText(sb.toString());
        }

    }


}
