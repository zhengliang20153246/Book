package com.example.book;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by LI on 2017/10/28.
 */

public class genggaisexActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genggaisex);

        Myuser.toolbartitle="书籍信息";

        //返回按钮事件**************
        ImageButton fanhui = (ImageButton) findViewById(R.id.fanhui);
        fanhui.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //finish();
                    Intent i = new Intent(genggaisexActivity.this, zhanghuguanliActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "返回成功！", Toast.LENGTH_SHORT).show();
                    finish();
                }
                return false;
            }
        });


        Button queding = (Button) findViewById(R.id.queding);
        //给btn1绑定监听事件
        queding.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // 给bnt1添加点击响应事件
                Toast.makeText(getApplicationContext(), "更改成功！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(genggaisexActivity.this, zhanghuguanliActivity.class);
                //启动
                startActivity(intent);
                finish();

            }
        });

    }
}

