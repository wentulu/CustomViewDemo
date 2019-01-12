package cn.stu.cusview.ruiz.customeviewdemo.dialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.stu.cusview.ruiz.customeviewdemo.R;

public class AlertDialogActivity extends AppCompatActivity implements View.OnClickListener{

    private Button show_progressDialog,show_alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);

        show_alertDialog = findViewById(R.id.show_alertDialog);
        show_progressDialog = findViewById(R.id.show_progressDialog);
        show_alertDialog.setOnClickListener(this);
        show_progressDialog.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.show_alertDialog:
                initAlertDialog();
                break;
            case R.id.show_progressDialog:
                showProgressDialog();
                break;
        }
    }


    private void showProgressDialog(){
       ProgressDialog progressDialog = new ProgressDialog(this);
       progressDialog.setTitle("Progress Dialog");
       progressDialog.setMessage("Loading...");
       progressDialog.setCancelable(true);
       progressDialog.show();

    }


    private  void initAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert Dialog")
                .setCancelable(true)
                .setMessage("This is a Alert Dialog!")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showToast("Yes, I agree.");
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showToast("No, I don't agree.");
                    }
                });
        builder.create().show();
    }


    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
