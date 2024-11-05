package com.example.android_sy3;
import android.app.ActionBar;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.util.SparseBooleanArray;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Activity extends AppCompatActivity {
    public static int a=0;
    private TextView testTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_homework3_1);
        testTextView = findViewById(R.id.test_text_view);
        createLoginDialog();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.homework3), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ListView listView = findViewById(R.id.listView);
        ListView listView1 = findViewById(R.id.listView1);
        String[] data1 = {"A", "B", "C", "D", "E", "F", "G", "H"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(Activity.this.peekAvailableContext(), android.R.layout.simple_list_item_1, data1);
        listView1.setAdapter(adapter1);

        List<ListItem> data = new ArrayList<>();
        data.add(new ListItem("lion", R.drawable.lion));
        data.add(new ListItem("Tiger", R.drawable.tiger));
        data.add(new ListItem("monkey", R.drawable.monkey));
        data.add(new ListItem("dog", R.drawable.dog));
        data.add(new ListItem("cat", R.drawable.cat));
        data.add(new ListItem("elephant", R.drawable.elephant));
        int nowselect= listView.getCheckedItemPosition();
        CustomAdapter adapter = new CustomAdapter(this, data);
        listView.setAdapter(adapter);
        Button bt1=findViewById(R.id.animalbutton);
        // 检查是否有选中项
        if(nowselect>=0)
            {
                bt1.setText(data.get(nowselect).text);
            } else
        {
                bt1.setText("未选中任何项");
            }

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SparseBooleanArray checkedItemPositions = listView1.getCheckedItemPositions();
                boolean isChecked = checkedItemPositions.get(position);
                TextView text=findViewById(R.id.selected_count_text);

                // 根据isChecked的值来更新你的数据模型（如果有）
                if (isChecked) {
                    // 执行选中操作相关的逻辑，比如更新数据模型中该项为选中状态
                    view.setBackgroundColor(getResources().getColor(R.color.orange));
                    a++;
                    String b=String.valueOf(a);
                    text.setText("已选择"+b+'项');

                } else {
                    view.setBackgroundColor(getResources().getColor(R.color.white));
                    // 执行取消选中操作相关的逻辑
                    a--;
                    String b=String.valueOf(a);
                    text.setText("已选择"+b+'项');
                }
                // 可能还需要通知适配器（如果有）数据已更改，以便更新视图
                adapter.notifyDataSetChanged();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.font_size_small) {
            testTextView.setTextSize(10);
        } else if (item.getItemId() == R.id.font_size_medium) {
            testTextView.setTextSize(16);
        } else if (item.getItemId() == R.id.font_size_large) {
            testTextView.setTextSize(20);
        } else if (item.getItemId() == R.id.normal_item) {
            Toast.makeText(this, "你点击了普通菜单项", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.font_color_red) {
            testTextView.setTextColor(Color.RED);
        } else if (item.getItemId() == R.id.font_color_black) {
            testTextView.setTextColor(Color.BLACK);
        } else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }
    private void createLoginDialog() {
        // 获取LayoutInflater来加载布局
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.activity_homework3_2, null);

        // 找到布局中的控件
        EditText usernameEditText = dialogView.findViewById(R.id.login_username);
        EditText passwordEditText = dialogView.findViewById(R.id.login_password);
        Button loginButton = dialogView.findViewById(R.id.login_button);

        // 创建AlertDialog.Builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 设置对话框的视图为我们加载的布局
        builder.setView(dialogView);

        // 创建对话框
        AlertDialog dialog = builder.create();

        // 设置登录按钮的点击事件监听器
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // 这里可以添加实际的登录验证逻辑，比如与服务器端验证等
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Activity.this, "用户名和密码不能为空", Toast.LENGTH_SHORT).
                            show();
                } else {
                    // 假设验证通过，关闭对话框
                    dialog.dismiss();
                    Toast.makeText(Activity.this, "登录成功", Toast.LENGTH_SHORT).
                            show();
                }
            }
        });

        // 显示对话框
        dialog.show();
    }

}