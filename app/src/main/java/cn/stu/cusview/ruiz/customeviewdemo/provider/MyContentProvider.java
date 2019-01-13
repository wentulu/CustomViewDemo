package cn.stu.cusview.ruiz.customeviewdemo.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import cn.stu.cusview.ruiz.customeviewdemo.db.MyDataBaseHelper;

public class MyContentProvider extends ContentProvider {

    MyDataBaseHelper mMyDataBaseHelper;

    private static final int BOOK_DIR = 1;
    private static final int BOOK_ITEM = 2;
    private static final int CATEGORY_DIR = 3;
    private static final int CATEGORY_ITEM = 4;

    private static final String AUTHORITY = "cn.stu.ruiz.custom";
    private static UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "Book", BOOK_DIR);
        uriMatcher.addURI(AUTHORITY, "BOOK/#", BOOK_ITEM);
        uriMatcher.addURI(AUTHORITY, "Category", CATEGORY_DIR);
        uriMatcher.addURI(AUTHORITY, "Category/#", CATEGORY_ITEM);
    }

    @Override
    public boolean onCreate() {
        mMyDataBaseHelper = MyDataBaseHelper.getInstance(getContext());
        return true;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase database = mMyDataBaseHelper.getWritableDatabase();
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd.cn.stu.ruiz.custom.provider.Book";

        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        return null;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase database = mMyDataBaseHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
               return database.query("Book",null,null,null,null,null,null);
        }

        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return 0;
    }
}
