package cn.stu.cusview.ruiz.customeviewdemo.socket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicBoolean;

import cn.stu.cusview.ruiz.customeviewdemo.R;

public class SocketClientActivity extends AppCompatActivity {

    private static final String TAG = "SocketClientActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_client);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
