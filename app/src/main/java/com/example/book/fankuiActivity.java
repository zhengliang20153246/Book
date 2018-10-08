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
 * Created by LI on 2017/10/27.
 */

public class fankuiActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fankui);

        Myuser.toolbartitle="书籍信息";

        //返回按钮事件**************
        ImageButton fanhui = (ImageButton) findViewById(R.id.fanhui);
        fanhui.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //finish();
                    Intent i = new Intent(fankuiActivity.this, gerenzhongxinActivity.class);
                    startActivity(i);
                    finish();
                    Toast.makeText(getApplicationContext(), "返回成功！", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });


        Button tijiao=(Button)findViewById(R.id.tijiao);
        //给btn1绑定监听事件
        tijiao.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // 给bnt1添加点击响应事件
                Toast.makeText(getApplicationContext(), "提交成功！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(fankuiActivity.this, gerenzhongxinActivity.class);
                //启动
                startActivity(intent);
                finish();

            }
        });

    }

}