package cn.stu.cusview.ruiz.customeviewdemo.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.stu.cusview.ruiz.customeviewdemo.R;
import cn.stu.cusview.ruiz.customeviewdemo.aidl.Book;

public class BookMemoryActivity extends AppCompatActivity {

    SQLiteDatabase mSQLiteDatabase;

    int index=0;

    private TextView books_info_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_memory);
        books_info_tv = findViewById(R.id.books_info_tv);
        mSQLiteDatabase = MyDataBaseHelper.getInstance(this).getWritableDatabase();
    }



    public void addBook(View view){
        ContentValues contentValues = new ContentValues();
        contentValues.put("bookName","book"+index);
        contentValues.put("price",78.9f);
        contentValues.put("author","author"+index);
        mSQLiteDatabase.insert("Book",null,contentValues);
        contentValues.clear();
        index++;
    }


    public void queryBookList(View view){
       Cursor cursor =  mSQLiteDatabase.query("Book",null,null,null,null,null,null);
        List<Book> booksList = null;
       if (cursor.moveToFirst()){
           booksList = new ArrayList<>();
           do {
               String bookName = cursor.getString(cursor.getColumnIndex("bookName"));
               String author = cursor.getString(cursor.getColumnIndex("author"));
               Book book = new Book(bookName,author);
               booksList.add(book);
           }while (cursor.moveToNext());
       }

       cursor.close();
       books_info_tv.setText(booksList.toString());
    }

}
