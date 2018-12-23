package cn.stu.cusview.ruiz.customeviewdemo.installedapp;

import android.app.Application;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.stu.cusview.ruiz.customeviewdemo.R;

public class InsatlledAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insatlled_app);
        startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            UsageStatsManager uam = (UsageStatsManager) getSystemService(USAGE_STATS_SERVICE);
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE,-1);
            Calendar calendar1 = Calendar.getInstance();
            List<UsageStats> usageStats = uam.queryUsageStats(UsageStatsManager.INTERVAL_MONTHLY,calendar.getTimeInMillis(),calendar1.getTimeInMillis());
            for (UsageStats usageStat : usageStats){
                usageStat.describeContents();
                Date date = new Date();
                date.setTime(usageStat.getLastTimeUsed());
                Date dateS = new Date();
                dateS.setTime(usageStat.getFirstTimeStamp());
                Log.e(usageStat.getPackageName()+"第一次使用",dateS.toString());
                Log.e(usageStat.getPackageName()+"最后使用",date.toString());
                Log.e(usageStat.getPackageName()+"使用时间",usageStat.getTotalTimeInForeground()/1000+"秒");
            }

        }
        getInstalledApps();

    }



    private List getInstalledApps(){

        List<String> appNames = new ArrayList<>();

        PackageManager pm = getPackageManager();
        List<PackageInfo> pms = pm.getInstalledPackages(0);
        Log.e("APP Num=",pms.size()+"所有APP");

        for (PackageInfo pmInfo : pms){
            if ((ApplicationInfo.FLAG_SYSTEM & pmInfo.applicationInfo.flags) !=0){
                continue;
            }
            Log.e("APP  packageName ",pmInfo.packageName);
            Log.e("APP  name",pmInfo.applicationInfo.loadLabel(pm).toString());
            appNames.add(pmInfo.applicationInfo.packageName);
        }
        Log.e("APP Num=",appNames.size()+"安装APP");
        return appNames;
    }

}
