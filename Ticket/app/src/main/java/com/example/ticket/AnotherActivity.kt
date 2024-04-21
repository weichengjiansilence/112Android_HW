package com.example.ticket

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AnotherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_another)

        // 從 intent 中獲取購票資訊
        val gender = intent.getStringExtra("gender")
        val type = intent.getStringExtra("type")
        val number = intent.getIntExtra("number", 0)
        val total = intent.getIntExtra("total", 0)

        // 將購票資訊設置到 TextView 中
        val ticketInfoTextView = findViewById<TextView>(R.id.textView5)
        ticketInfoTextView.text = "性別：$gender\n票種：$type\n張數：$number 張\n總價：$total 元"

        // 設置返回上一頁按鈕的點擊事件監聽器
        val backButton = findViewById<Button>(R.id.button2)
        backButton.setOnClickListener {
            finish() // 結束當前活動，返回上一個活動
        }
    }
}
