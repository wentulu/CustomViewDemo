package cn.stu.cusview.ruiz.customeviewdemo.file;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.prefs.Preferences;

import cn.stu.cusview.ruiz.customeviewdemo.R;

public class SharedPreferenceActivity extends AppCompatActivity {

    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);

        mSharedPreferences = getSharedPreferences("NAME",MODE_PRIVATE);
//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mSharedPreferences = getApplicationContext().getSharedPreferences("Name",MODE_PRIVATE);
    }




    public void save(String key,String value){
        mSharedPreferences.edit().putString(key,value).commit();
    }

}
