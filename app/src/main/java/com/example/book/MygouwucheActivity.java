package com.example.book;

import android.app.ProgressDialog;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.widget.AdapterView.OnItemClickListener;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.bmob.v3.listener.UpdateListener;


public class MygouwucheActivity extends AppCompatActivity implements OnItemClickListener
{
    private List<Book> gouewuchebooklist=new ArrayList<>();
    private String sql1;
    private String sql;
    private String username="1";
    private ListView listView;
    private  ProgressDialog progressDialog;
    private  MygouwuchebookAdapter mygouwuchebookAdapter;
    private TextView zongjiatext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mygouwuche);
        Toolbar toolbar=(Toolbar)findViewById(R.id.Mygouwuchetoolbar);
        toolbar.setTitle("");
        toolbar.inflateMenu(R.menu.menu_mygouwuche);
        setSupportActionBar(toolbar);
        sql1="select * from Gouwuche where username='"+username+"'";
        listView=(ListView)findViewById(R.id.mygouwuchelistview);
        progressDialog= new ProgressDialog(MygouwucheActivity.this);
        progressDialog.setMessage("正在加载.....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        zongjiatext=(TextView)findViewById(R.id.Mygouwuchezongjia);

        zhao();
        CheckBox checkBoxquanxuan=(CheckBox)findViewById(R.id.mygouwuchecheckBox);
        checkBoxquanxuan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked)
            {
                mygouwuchebookAdapter.quanxuan(isChecked);
                mygouwuchebookAdapter.notifyDataSetChanged();

            }});



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mygouwuche, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Mygouwuchezhuye:
                Toast.makeText(MygouwucheActivity.this, "主页", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Mygouwuchegeren:
                Toast.makeText(MygouwucheActivity.this, "个人", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Mygouwuchemydianpu:
                Toast.makeText(MygouwucheActivity.this, "我的店铺", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Mygouwuchedingdan:
                Toast.makeText(MygouwucheActivity.this, "我的订单", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private  void zhao()
    {
        new BmobQuery<Gouwuche>().doSQLQuery(sql1,new SQLQueryListener<Gouwuche>(){

            @Override
            public void done(BmobQueryResult<Gouwuche> result, BmobException e) {
                if(e ==null)
                {
                    List<Gouwuche> list = (List<Gouwuche>) result.getResults();
                    if(list!=null && list.size()>0)
                    {
                        for(int i=0;i<list.size();i++)
                        {
                            Gouwuche linshigouwuche=new Gouwuche();
                            linshigouwuche=list.get(i);
                            sql="select * from Book where objectId='"+linshigouwuche.getBookid()+"'";
                            String gouwucheid=linshigouwuche.getObjectId();
                            chushihua(gouwucheid);
                        }

                    }
                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "购物车空空如也！", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "购物车查找失败！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void chushihua(final String gouwucheid)
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
                            linshibook.setGouwucheid(gouwucheid);
                            gouewuchebooklist.add(linshibook);
                        }
                        progressDialog.dismiss();
                        mygouwuchebookAdapter=new MygouwuchebookAdapter(MygouwucheActivity.this,R.layout.mygouwuchebook,gouewuchebooklist,mListener,zongjiatext);
                        listView.setAdapter(mygouwuchebookAdapter);
                        listView.setOnItemClickListener(MygouwucheActivity.this);

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
                    Toast.makeText(getApplicationContext(), "书籍查找失败！", Toast.LENGTH_SHORT).show();
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

    public void dialogshow(final String gouwucheid)
    {
        AlertDialog dialog=new AlertDialog.Builder(MygouwucheActivity.this).create();
        dialog.setTitle("提示");
        dialog.setMessage("确认删除？");
        dialog.setCancelable(false);
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE,"取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });
        dialog.setButton(DialogInterface.BUTTON_POSITIVE,"确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                final Gouwuche gouwuche = new Gouwuche();
                gouwuche.setObjectId(gouwucheid);
                gouwuche.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e==null)
                        {
                            Intent intent = new Intent(MygouwucheActivity.this, MygouwucheActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(MygouwucheActivity.this,"删除失败！"+e,Toast.LENGTH_LONG).show();
                        }
                    }

                });

            }
        });
        dialog.show();
        Button btnPositive = dialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE);
        Button btnNegative = dialog.getButton(android.app.AlertDialog.BUTTON_NEGATIVE);
        btnNegative.setTextColor(Color.BLACK);
        btnNegative.setTextSize(15);
        btnPositive.setTextColor(Color.BLACK);
        btnPositive.setTextSize(15);
    }
    private MygouwuchebookAdapter.MyClickListener mListener = new MygouwuchebookAdapter.MyClickListener()
    {

        public void myOnClick(int position, View v)
        {
            dialogshow(gouewuchebooklist.get(position).getGouwucheid());
        }

    };

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        Toast.makeText(getApplicationContext(), "item"+i, Toast.LENGTH_SHORT).show();

    }
}
