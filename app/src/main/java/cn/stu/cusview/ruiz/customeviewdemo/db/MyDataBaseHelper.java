package cn.stu.cusview.ruiz.customeviewdemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
* 创建下来的数据库可以在data数据下对应包名的database中找到，
 * 1、使用adb shell 进入shell模式。
 * 2、cd进入目录
 * 3、sqlite3 dbname.db进入数据库
 * 4、.table查看有几张表
 * 5、.schema查看表的创建逻辑
* **/
public class MyDataBaseHelper extends SQLiteOpenHelper {


    private static final int DB_VERSION = 2;//数据库版本号
    private static final String DB_NAME = "custom_view.db";//数据库名称


    private volatile static MyDataBaseHelper mInstance;//实例


    private static String create_table_book = "create table Book(" +
            "bookId integer primary key autoincrement," +
            "author text," +
            "price real," +
            "bookName text" +
            ")";

    private static String create_table_category = "create table Category(" +
            "id integer primary key autoincrement," +
            "categoryName text," +
            "categoryId text" +
            ")";


    /**
     * 获取实例的方法
     * */
    public static MyDataBaseHelper getInstance(Context context) {
        if (mInstance == null) {
            synchronized (MyDataBaseHelper.class) {
                if (mInstance == null) {
                    mInstance = new MyDataBaseHelper(context.getApplicationContext());
                }
            }
        }
        return mInstance;
    }

    private MyDataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table_book);
        db.execSQL(create_table_category);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Category");
        onCreate(db);
    }
}
