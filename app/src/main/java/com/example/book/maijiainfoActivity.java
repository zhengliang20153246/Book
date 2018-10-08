package com.example.book;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LI on 2017/10/29.
 */

public class maijiainfoActivity extends AppCompatActivity {

    private List<listmaijiainfoClass> maijialist = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maijiainfo);

        Myuser.toolbartitle="书籍信息";

        //初始化
        init();
        //init2();
        //adapter适配器*************
        maijiainfoAdapter adapter = new maijiainfoAdapter(maijiainfoActivity.this,R.layout.listmaijia,maijialist);
        ListView listView = (ListView)findViewById(R.id.listmaijia);
        listView.setAdapter(adapter);


        //返回按钮事件**************
        ImageButton maijiainfofanhui = (ImageButton) findViewById(R.id.maijiainfofanhui);
        maijiainfofanhui.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //finish();
                    Intent i = new Intent(maijiainfoActivity.this, gerenzhongxinActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "返回成功！", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });


    }

    private void init()
    {
        listmaijiainfoClass first = new listmaijiainfoClass("校园旧书","132********","L*********","190*******",R.drawable.shu);
        maijialist.add(first);
        listmaijiainfoClass second = new listmaijiainfoClass("校园旧书","132********","L*********","190*******",R.drawable.shu);
        maijialist.add(second);
        listmaijiainfoClass san = new listmaijiainfoClass("校园旧书","132********","L*********","190*******",R.drawable.shu);
        maijialist.add(san);
        listmaijiainfoClass si = new listmaijiainfoClass("校园旧书","132********","L*********","190*******",R.drawable.shu);
        maijialist.add(si);

    }

}