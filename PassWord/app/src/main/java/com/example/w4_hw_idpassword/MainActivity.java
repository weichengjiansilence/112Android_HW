package com.example.w4_hw_idpassword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public  void login(View view){
        EditText id=(EditText) findViewById(R.id.editTextID);
        EditText pw=(EditText) findViewById(R.id.editTextPW);
        EditText result=(EditText) findViewById(R.id.editTextResult);
        if(id.getText().toString().equals("weichengjian")){
            if(pw.getText().toString().equals("930201")){
                result.setText("登入成功");
            }
            else{
                result.setText("密碼錯誤");
            }
        }
        else{
            result.setText("帳號錯誤");
        }
    }
}