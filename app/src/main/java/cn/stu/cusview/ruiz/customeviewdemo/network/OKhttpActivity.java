package cn.stu.cusview.ruiz.customeviewdemo.network;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import cn.stu.cusview.ruiz.customeviewdemo.R;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 9.0开始不再支持http
 * */

public class OKhttpActivity extends AppCompatActivity {

    private static final String TAG = "OKhttpActivity";

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        mWebView = findViewById(R.id.web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
    }



    public void onGetData(View view){


        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder().url("https://m.baidu.com").build();

                OkHttpClient client = new OkHttpClient.Builder().build();
                try {
                    Response response = client.newCall(request).execute();
                    final String result = response.body().string();
                    Log.e(TAG, result);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                mWebView.loadData(result,"text/html","utf-8");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

}
