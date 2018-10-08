package com.example.book;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

/**
 * Created by LI on 2017/11/2.
 */

public class fenleiliulanActivity extends AppCompatActivity {
    private RollPagerView mRollViewPager;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenleiliulan);
        Toolbar toolbar=(Toolbar)findViewById(R.id.fenleitoolbar);
        toolbar.setTitle("");
        toolbar.inflateMenu(R.menu.mune_bookxinxi);
        setSupportActionBar(toolbar);

        mRollViewPager = (RollPagerView) findViewById(R.id.fenleiroll_view_pager);

        //设置播放时间间隔
        mRollViewPager.setPlayDelay(2000);
        //设置透明度
        mRollViewPager.setAnimationDurtion(500);
        //设置适配器
        mRollViewPager.setAdapter(new FenleilunboAdapter());

        //设置指示器（顺序依次）
        //自定义指示器图片
        //设置圆点指示器颜色
        //设置文字指示器
        //隐藏指示器
        //mRollViewPager.setHintView(new IconHintView(this, R.drawable.point_focus, R.drawable.point_normal));
        mRollViewPager.setHintView(new ColorPointHintView(this, Color.GRAY,Color.WHITE));

        Myuser.toolbartitle="书籍信息";

        //返回按钮事件**************
        final ImageButton fenleiliulanfanhui = (ImageButton) findViewById(R.id.fenleiliulanfanhui);
        fenleiliulanfanhui.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //finish();
                    Intent i = new Intent(fenleiliulanActivity.this, gerenzhongxinActivity.class);
                    startActivity(i);
                    finish();
                    Toast.makeText(getApplicationContext(), "返回成功！", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        //Button点击事件
        Button button1 = (Button)findViewById(R.id.button1);
                //给btn1绑定监听事件
        button1.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Myuser.toolbartitle="二手教材";
                        String searchtiaojian = "4";
                        Intent intent = new Intent(fenleiliulanActivity.this, bookActivity.class);
                        intent.putExtra("searchname", "二手教材");
                        intent.putExtra("searchtiaojian", searchtiaojian);
                        startActivity(intent);
                    }
                });

        //Button点击事件
        Button button2 = (Button)findViewById(R.id.button2);
        //给btn2绑定监听事件
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Myuser.toolbartitle="IT";
                String searchtiaojian = "4";
                Intent intent = new Intent(fenleiliulanActivity.this, bookActivity.class);
                intent.putExtra("searchname", "IT");
                intent.putExtra("searchtiaojian", searchtiaojian);
                startActivity(intent);

            }
        });

        //Button点击事件
        Button button3 = (Button)findViewById(R.id.button3);
        //给btn3绑定监听事件
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Myuser.toolbartitle="数学";
                String searchtiaojian = "4";
                Intent intent = new Intent(fenleiliulanActivity.this, bookActivity.class);
                intent.putExtra("searchname", "数学");
                intent.putExtra("searchtiaojian", searchtiaojian);
                startActivity(intent);

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mune_bookxinxi, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Bookxinxizhuye:
                Toast.makeText(fenleiliulanActivity.this, "收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Bookxinxigeren:
                Toast.makeText(fenleiliulanActivity.this, "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Bookxinximydianpu:
                Toast.makeText(fenleiliulanActivity.this, "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Bookxinxigouwuche:
                Toast.makeText(fenleiliulanActivity.this, "字号", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Bookxinxidingdan:
                Toast.makeText(fenleiliulanActivity.this, "模式", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
