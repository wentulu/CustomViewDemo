package cn.stu.cusview.ruiz.customeviewdemo.network;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cn.stu.cusview.ruiz.customeviewdemo.R;

public class URLConActivity extends AppCompatActivity {


    private static final String TAG = "URLConActivity";

    private TextView resulu_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urlcon);
        resulu_tv = findViewById(R.id.result_tv);
    }


    public void onGetData(View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://www.baidu.com");

                    final StringBuilder stringBuilder = new StringBuilder();
                    try {
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                        urlConnection.connect();
                        InputStream inputStream = urlConnection.getInputStream();
                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        String line = null;
                        while ((line = bufferedReader.readLine()) != null) {
                            stringBuilder.append(line);
                            Log.e(TAG, line);
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                resulu_tv.setText(Html.fromHtml(stringBuilder.toString()));
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }


}
