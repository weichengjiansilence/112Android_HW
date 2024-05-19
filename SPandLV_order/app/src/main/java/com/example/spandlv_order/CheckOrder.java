package com.example.spandlv_order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CheckOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkorder);

        TextView lblOutput = findViewById(R.id.lblOutput);
        String outputStr = getIntent().getStringExtra("outputStr");
        lblOutput.setText(outputStr);

        // 获取返回按钮并添加点击事件监听器
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 返回上一页
                finish();
            }
        });
    }
}
