package com.example.claculator;


import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;

enum State {FirstNumberInput, OperatorInputed, NumberInput}
enum OP { None, Add, Sub, Mul, Div}

public class MainActivity extends AppCompatActivity {

    private double theValue = 0;
    private double operand1 = 0, operand2 = 0;
    private OP op = OP.None;
    private State state = State.FirstNumberInput;

    private boolean button = false; // 標記小數點是否被按下

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        GridLayout keysGL = (GridLayout) findViewById(R.id.keys);

        int kbHeight = (int) (keysGL.getHeight() / keysGL.getRowCount());
        int kbWidth = (int) (keysGL.getWidth() / keysGL.getColumnCount());
        Button btn;
        for (int i = 0; i < keysGL.getChildCount(); i++) {
            btn = (Button) keysGL.getChildAt(i);
            btn.setHeight(kbHeight);
            btn.setWidth(kbWidth);
            btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
        }
    }


    public void onWindowFocusChanged(boolean hasFocus) {
        GridLayout keysGL = (GridLayout) findViewById(R.id.keys);

        int kbHeight = (int) (keysGL.getHeight() / keysGL.getRowCount());
        int kbWidth = (int) (keysGL.getWidth() / keysGL.getColumnCount());

        Button btn;

        for (int i = 0; i < keysGL.getChildCount(); i++) {
            btn = (Button) keysGL.getChildAt(i);
            btn.setHeight(kbHeight);
            btn.setWidth(kbWidth);
            btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
        }
    }
    public void processKeyInput(View view) {
        Button b = (Button) view;
        String bstr = b.getText().toString();
        EditText edt = (EditText) findViewById(R.id.display);
        String currentText = edt.getText().toString();

        switch (bstr) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                if (state == State.OperatorInputed) {
                    currentText = "";
                    state = State.NumberInput;
                }
                currentText += bstr;
                edt.setText(currentText);
                break;

            case ".":
                if (!currentText.contains(".")) {
                    currentText += ".";
                    edt.setText(currentText);
                }
                if (state == State.OperatorInputed) {
                    state = State.NumberInput;
                }
                break;

            // 操作符按鈕的處理
            case "+":
            case "-":
            case "*":
            case "/":
                if (state != State.FirstNumberInput) {
                    operand1 = Double.parseDouble(currentText);
                    switch (bstr) {
                        case "+":
                            op = OP.Add;
                            break;
                        case "-":
                            op = OP.Sub;
                            break;
                        case "*":
                            op = OP.Mul;
                            break;
                        case "/":
                            op = OP.Div;
                            break;
                    }
                    state = State.OperatorInputed;
                } else {
                    operand1 = Double.parseDouble(currentText);
                    switch (bstr) {
                        case "+":
                            op = OP.Add;
                            break;
                        case "-":
                            op = OP.Sub;
                            break;
                        case "*":
                            op = OP.Mul;
                            break;
                        case "/":
                            op = OP.Div;
                            break;
                    }
                    state = State.OperatorInputed;
                }
                break;

            case "=":
                if (state == State.NumberInput) {
                    operand2 = Double.parseDouble(currentText);
                    switch (op) {
                        case Add:
                            theValue = operand1 + operand2;
                            break;
                        case Sub:
                            theValue = operand1 - operand2;
                            break;
                        case Mul:
                            theValue = operand1 * operand2;
                            break;
                        case Div:
                            theValue = operand1 / operand2;
                            break;
                    }
                    edt.setText(String.valueOf(theValue));
                    state = State.OperatorInputed;
                }
                break;

            case "Clear":
                currentText = "";
                theValue = 0;
                operand1 = 0;
                operand2 = 0;
                op = OP.None;
                state = State.FirstNumberInput;
                edt.setText("0");
                break;

            case "Back":
                if (currentText.length() > 0) {
                    currentText = currentText.substring(0, currentText.length() - 1);
                    edt.setText(currentText);
                }
                break;
        }
    }
}