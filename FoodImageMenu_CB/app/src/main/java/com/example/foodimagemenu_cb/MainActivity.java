package com.example.foodimagemenu_cb;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CheckBox[] checkBoxes;
    private ImageView[] imageViews;
    private TextView txvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txvShow = findViewById(R.id.showOrder);

        // 初始化 CheckBox 和 ImageView
        checkBoxes = new CheckBox[] {
                findViewById(R.id.chk1),
                findViewById(R.id.chk2),
                findViewById(R.id.chk3),
                findViewById(R.id.chk4),
                findViewById(R.id.chk5)
        };

        imageViews = new ImageView[] {
                findViewById(R.id.output1),
                findViewById(R.id.output2),
                findViewById(R.id.output3),
                findViewById(R.id.output4),
                findViewById(R.id.output5)
        };

        // 隱藏所有圖片
        for (ImageView imageView : imageViews) {
            imageView.setVisibility(View.GONE);
        }

        // 為每個 CheckBox 設置點擊監聽器
        for (CheckBox checkBox : checkBoxes) {
            checkBox.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        boolean anyChecked = false;

        // 檢查是否有任何一個 CheckBox 被選中
        for (CheckBox checkBox : checkBoxes) {
            if (checkBox.isChecked()) {
                anyChecked = true;
                break;
            }
        }

        // 根據狀態更新 TextView 和 ImageView
        if (anyChecked) {
            txvShow.setText("你點的餐點如下");
            for (int i = 0; i < checkBoxes.length; i++) {
                if (checkBoxes[i].isChecked()) {
                    imageViews[i].setVisibility(View.VISIBLE);
                } else {
                    imageViews[i].setVisibility(View.GONE);
                }
            }
        } else {
            txvShow.setText("請點餐");
            // 當沒有選中任何 CheckBox 時隱藏所有圖片
            for (ImageView imageView : imageViews) {
                imageView.setVisibility(View.GONE);
            }
        }
    }
}

