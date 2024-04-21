package com.example.ticket

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent


import android.widget.Button


/*class MainActivity : AppCompatActivity() {
    private  TextView output;

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInstsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}*/

class MainActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener, TextWatcher {
    private lateinit var output: TextView
    private lateinit var rgGender: RadioGroup
    private lateinit var rgType: RadioGroup
    private lateinit var numberEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        output = findViewById(R.id.lblOutput)
        rgGender = findViewById(R.id.rgGender)
        rgType = findViewById(R.id.rgType)
        numberEditText = findViewById(R.id.number)

        rgGender.setOnCheckedChangeListener { _, _ -> updateOutput() }
        rgType.setOnCheckedChangeListener { _, _ -> updateOutput() }
        numberEditText.addTextChangedListener(this)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val genderRadioButton = findViewById<RadioButton>(rgGender.checkedRadioButtonId)
            val typeRadioButton = findViewById<RadioButton>(rgType.checkedRadioButtonId)
            val number = numberEditText.text.toString().toIntOrNull() ?: 0
            val price = when (typeRadioButton?.id) {
                R.id.rgbAdult -> 500
                R.id.rgbChild -> 250
                R.id.rgbStudent -> 400
                else -> 0
            }
            val total = price * number

            val intent = Intent(this, AnotherActivity::class.java)
            intent.putExtra("gender", genderRadioButton?.text.toString())
            intent.putExtra("type", typeRadioButton?.text.toString())
            intent.putExtra("number", number)
            intent.putExtra("total", total)
            startActivity(intent)
        }
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        updateOutput()
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        updateOutput()
    }

    override fun afterTextChanged(s: Editable?) {}

    private fun updateOutput() {
        val genderRadioButton = findViewById<RadioButton>(rgGender.checkedRadioButtonId)
        val typeRadioButton = findViewById<RadioButton>(rgType.checkedRadioButtonId)
        val number = numberEditText.text.toString().toIntOrNull() ?: 0

        val price = when (typeRadioButton?.id) {
            R.id.rgbAdult -> 500
            R.id.rgbChild -> 250
            R.id.rgbStudent -> 400
            else -> 0
        }

        val total = price * number

        output.text = "性別：${genderRadioButton?.text ?: ""}\n票種：${typeRadioButton?.text ?: ""}\n張數：$number 張\n總價：$total 元"
    }
}


