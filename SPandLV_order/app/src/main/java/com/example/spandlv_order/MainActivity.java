package com.example.spandlv_order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String[] types, main, side, drink;
    private Spinner spTypes;
    private ListView lvtype;
    private String OutputOrder="";
    private String Strmain = "";
    private String Strside = "";
    private String Strdrink = "";
    private TextView output;
    private ArrayAdapter<String> listAdapter;
    private String currentType;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 加载菜单资源
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // 菜单项选择事件处理
        int itemId = item.getItemId();
        if(itemId == R.id.order){
            // 跳转到订单信息页面
            Intent intent = new Intent(MainActivity.this, CheckOrder.class);
            intent.putExtra("outputStr", OutputOrder);
            startActivity(intent);

        }
        else if(itemId == R.id.cancel){
            // 取消订单
            Cancel(null);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = findViewById(R.id.lblOutput);
        Strmain = getString(R.string.choice);
        Strside = getString(R.string.choice);
        Strdrink = getString(R.string.choice);

        // 初始化界面元素
        types = getResources().getStringArray(R.array.types);
        spTypes = findViewById(R.id.SideOrder);
        ArrayAdapter<String> adpTypes = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, types);
        spTypes.setAdapter(adpTypes);

        main = getResources().getStringArray(R.array.main);
        side = getResources().getStringArray(R.array.side);
        drink = getResources().getStringArray(R.array.drink);
        spTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 更新当前选项类型
                currentType = types[position];
                // 更新列表视图
                updateListView(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        lvtype = findViewById(R.id.listview);
        listAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1);
        lvtype.setAdapter(listAdapter);
        // 设置列表项点击事件监听器
        lvtype.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 处理列表项点击事件
                handleItemClick(currentType, position);
            }
        });
    }

    private void handleItemClick(String currentType, int position) {
        // 根据当前类型处理列表项点击事件
        switch (currentType) {
            case "main":
                String selectedMain = main[position];
                Strmain = selectedMain;
                break;
            case "side":
                String selectedSide = side[position];
                Strside = selectedSide;
                break;
            case "drink":
                String selectedDrink = drink[position];
                Strdrink = selectedDrink;
                break;
        }
        // 更新订单信息文本
        OutputOrder = getString(R.string.strmain) + Strmain +
                getString(R.string.strside) + Strside +
                getString(R.string.strdrink) + Strdrink;
        output.setText(OutputOrder); // 使用setText方法设置文本而不是setHint
    }

    public void updateListView(int index) {
        // 清空列表视图的当前数据
        listAdapter.clear();

        // 根据索引更新列表数据
        switch (index) {
            case 0: // 主菜
                listAdapter.addAll(main);
                currentType = "main";
                break;
            case 1: // 配菜
                listAdapter.addAll(side);
                currentType = "side";
                break;
            case 2: // 饮料
                listAdapter.addAll(drink);
                currentType = "drink";
                break;
        }
    }

    public void Cancel(View view) {
        // 取消订单，重置订单信息
        Strmain = getString(R.string.choice);
        Strside = getString(R.string.choice);
        Strdrink = getString(R.string.choice);
        // 重置订单信息文本
        OutputOrder = getString(R.string.strmain) + Strmain +
                getString(R.string.strside) + Strside +
                getString(R.string.strdrink) + Strdrink;
        output.setText(OutputOrder);
        spTypes.setSelection(0);
        updateListView(0); // 回到主菜类型
    }
}
