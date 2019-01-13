package cn.stu.cusview.ruiz.customeviewdemo.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import cn.stu.cusview.ruiz.customeviewdemo.db.MyDataBaseHelper;

/**
 * 取值使用的方法，在另外一个app，目前只是实现了查询
 * <p>
 * private String uri = "content://cn.stu.ruiz.custom/Book";
 * <p>
 * <p>
 * Cursor cursor = getContentResolver().query(Uri.parse(uri),null,null,null,null);
 * if (cursor.moveToFirst()){
 * StringBuilder stringBuilder = new StringBuilder();
 * do {
 * String bookName = cursor.getString(cursor.getColumnIndex("bookName"));
 * String author = cursor.getString(cursor.getColumnIndex("author"));
 * stringBuilder.append("["+bookName+":"+author+"]^");
 * }while (cursor.moveToNext());
 * book_tv.setText(stringBuilder.toString());
 * }
 */

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
        switch (uriMatcher.match(uri)) {
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
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                return database.query("Book", null, null, null, null, null, null);
        }

        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return 0;
    }
}
