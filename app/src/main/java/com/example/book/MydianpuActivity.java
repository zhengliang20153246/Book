package com.example.book;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;

public class MydianpuActivity extends AppCompatActivity {
    private List<Book>mydianpubookclassList=new ArrayList<>();
    private String sql;
    private String username="1";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydianpu);
        Toolbar toolbar=(Toolbar)findViewById(R.id.Mydianputoolbar);
        toolbar.setTitle("");
        toolbar.inflateMenu(R.menu.menu_mydianpu);
        setSupportActionBar(toolbar);
        progressDialog= new ProgressDialog(MydianpuActivity.this);
        progressDialog.setMessage("正在加载.....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        sql="select * from Book where username='"+username+"'";
        chushihua();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mydianpu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Mydianpuzhuye:
                Toast.makeText(MydianpuActivity.this, "收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Mydianpugeren:
                Toast.makeText(MydianpuActivity.this, "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Mydianpugouwuche:
                Toast.makeText(MydianpuActivity.this, "字号", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Mydianpudingdan:
                Toast.makeText(MydianpuActivity.this, "模式", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void chushihua()
    {
        new BmobQuery<Book>().doSQLQuery(sql,new SQLQueryListener<Book>(){

            @Override
            public void done(BmobQueryResult<Book> result, BmobException e) {
                if(e ==null)
                {
                    List<Book> list = (List<Book>) result.getResults();
                    if(list!=null && list.size()>0)
                    {
                        for(int i=0;i<list.size();i++)
                        {
                            Book linshibook=new Book();
                            linshibook=list.get(i);
                            linshibook.setPicurl1(linshibook.getPic1().getFileUrl());
                            linshibook.setPicurl2(linshibook.getPic2().getFileUrl());
                            String date1=linshibook.getCreatedAt();
                            date1=date1.substring(0,10);
                            linshibook.setDate(stringToDate(date1));
                            mydianpubookclassList.add(linshibook);
                        }
                        progressDialog.dismiss();
                        MydianpubookAdapter mydianpurecucleAdapter=new MydianpubookAdapter(MydianpuActivity.this,R.layout.mydianpubook,mydianpubookclassList);
                        ListView listView=(ListView)findViewById(R.id.mydianpubooklistview);
                        listView.setAdapter(mydianpurecucleAdapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                        {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, final int position, long id)
                            {

                                Toast.makeText(MydianpuActivity.this,position+"sss",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "没有改类书籍信息！", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "查找失败！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public Date stringToDate(String str) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            // Fri Feb 24 00:00:00 CST 2012
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 2012-02-24
        date = java.sql.Date.valueOf(str);

        return date;
    }



}
