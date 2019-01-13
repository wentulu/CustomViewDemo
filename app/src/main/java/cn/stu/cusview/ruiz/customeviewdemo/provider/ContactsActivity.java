package cn.stu.cusview.ruiz.customeviewdemo.provider;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.stu.cusview.ruiz.customeviewdemo.R;


/**
 * 没有使用权限的运行时，需要手动去设置界面去设置。
 * */
public class ContactsActivity extends AppCompatActivity {


    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        mTextView = findViewById(R.id.contacts_tv);
    }


    public void getContacts(View view){

       Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,null,null,null);
        if (cursor.moveToFirst()){
            StringBuilder sb = new StringBuilder();
            do {

                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                sb.append("[name:"+name+" Num:"+number+"]\n");

            }while (cursor.moveToNext());
            mTextView.setText(sb.toString());
        }

        cursor.close();

    }


}
