package com.example.book;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Handler;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SQLQueryListener;

public class MydingdanActivity extends AppCompatActivity {
    private TextView statustextview;
    private List<dingdan> dingdanList=new ArrayList<>();
    private List<Book> bookList=new ArrayList<>();
    private ListView listView;
    private  ProgressDialog progressDialog;
    private String sql;
    private Handler handler=null;
    private dingdan linshidingdan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydingdan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.Mydingdantoolbar);
        toolbar.setTitle("");
        toolbar.inflateMenu(R.menu.mune_bookxinxi);
        setSupportActionBar(toolbar);
        statustextview = (TextView) findViewById(R.id.Mydingdanstatus);
        listView = (ListView) findViewById(R.id.Mydingdanlistview);
        progressDialog = new ProgressDialog(MydingdanActivity.this);
        progressDialog.setMessage("正在加载.....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        sql = "select * from dingdan where username='1'";
        zhao();

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
                Toast.makeText(MydingdanActivity.this, "收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Bookxinxigeren:
                Toast.makeText(MydingdanActivity.this, "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Bookxinximydianpu:
                Toast.makeText(MydingdanActivity.this, "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Bookxinxigouwuche:
                Toast.makeText(MydingdanActivity.this, "字号", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Bookxinxidingdan:
                Toast.makeText(MydingdanActivity.this, "模式", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public  void zhao()
    {
        new BmobQuery<dingdan>().doSQLQuery(sql,new SQLQueryListener<dingdan>(){

            @Override
            public void done(BmobQueryResult<dingdan> result, BmobException e) {
                if(e ==null)
                {
                    List<dingdan> list = (List<dingdan>) result.getResults();
                    if(list!=null && list.size()>0)
                    {
                        for(int i=0;i<list.size();i++)
                        {
                            linshidingdan=new dingdan();
                            linshidingdan=list.get(i);
                            dingdanList.add(linshidingdan);
                        }
                        progressDialog.dismiss();
                        MydingdanAdapter mydingdanAdapter=new MydingdanAdapter(MydingdanActivity.this,R.layout.mydingdanbook,dingdanList);
                        listView.setAdapter(mydingdanAdapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                        {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, final int position, long id)
                            {
                                Intent intent=new Intent(MydingdanActivity.this,BookxinxiActivity.class);
                                intent.putExtra("bookid",dingdanList.get(position).getBookid());
                                startActivity(intent);

                            }
                        });

                    }
                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "订单空空如也！", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void chushihua(String bookid)
    {



    }

}
