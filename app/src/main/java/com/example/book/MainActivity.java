package com.example.book;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import cn.bmob.v3.Bmob;

public class MainActivity extends AppCompatActivity
{
    private int width;
    private RollPagerView mRollViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bmob.initialize(this, "a7219d8ddbcd940c1b73012bd14eaefd");

        //图片轮播

        mRollViewPager = (RollPagerView) findViewById(R.id.mainroll_view_pager);

        //设置播放时间间隔
        mRollViewPager.setPlayDelay(2000);
        //设置透明度
        mRollViewPager.setAnimationDurtion(500);
        //设置适配器
        mRollViewPager.setAdapter(new LunboAdapter());

        //设置指示器（顺序依次）
        //自定义指示器图片
        //设置圆点指示器颜色
        //设置文字指示器
        //隐藏指示器
        //mRollViewPager.setHintView(new IconHintView(this, R.drawable.point_focus, R.drawable.point_normal));
        mRollViewPager.setHintView(new ColorPointHintView(this, Color.GRAY,Color.WHITE));



        DisplayMetrics dm = getResources().getDisplayMetrics();
        width = (dm.widthPixels-15*2-35*3)/4;
        ImageButton zhuwogouwuche=(ImageButton)findViewById(R.id.zhuwogouwuche);
        ImageButton zhuwogeren=(ImageButton)findViewById(R.id.zhuwogeren) ;
        ImageButton zhuwodianpu=(ImageButton)findViewById(R.id.zhuwodianpu) ;
        ImageButton zhuwodingdan=(ImageButton)findViewById(R.id.zhuwodingdan) ;
        ImageButton zhuwofenlei=(ImageButton)findViewById(R.id.zhuwofenlei);
        ImageButton zhuworexiao=(ImageButton)findViewById(R.id.zhuworexiao) ;
        ImageButton zhuwozuixin=(ImageButton)findViewById(R.id.zhuwozuixin) ;
        ImageButton zhuwogonglue=(ImageButton)findViewById(R.id.zhuwogonglue) ;

        LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) zhuwogouwuche.getLayoutParams();
        params.weight=width;
        params.height=width-45;
        zhuwogouwuche.setLayoutParams(params);

        params= (LinearLayout.LayoutParams) zhuwogeren.getLayoutParams();
        params.weight=width;
        params.height=width-45;
        zhuwogeren.setLayoutParams(params);

        params= (LinearLayout.LayoutParams) zhuwodianpu.getLayoutParams();
        params.weight=width;
        params.height=width-45;
        zhuwodianpu.setLayoutParams(params);

        params= (LinearLayout.LayoutParams) zhuwodingdan.getLayoutParams();
        params.weight=width;
        params.height=width-45;
        zhuwodingdan.setLayoutParams(params);

        params= (LinearLayout.LayoutParams) zhuwofenlei.getLayoutParams();
        params.weight=width;
        params.height=width-45;
        zhuwofenlei.setLayoutParams(params);

        params= (LinearLayout.LayoutParams) zhuworexiao.getLayoutParams();
        params.weight=width;
        params.height=width-45;
        zhuworexiao.setLayoutParams(params);

        params= (LinearLayout.LayoutParams) zhuwozuixin.getLayoutParams();
        params.weight=width;
        params.height=width-45;
        zhuwozuixin.setLayoutParams(params);

        params= (LinearLayout.LayoutParams) zhuwogonglue.getLayoutParams();
        params.weight=width;
        params.height=width-45;
        zhuwogonglue.setLayoutParams(params);

        //购物车
        zhuwogouwuche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable(MainActivity.this))
                {
                    Intent intent=new Intent(MainActivity.this,MygouwucheActivity.class);
                    startActivity(intent);
                }
                else
                {
                    dialogshow();
                }
            }
        });
        //个人中心
        zhuwogeren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable(MainActivity.this))
                {
                    Intent intent=new Intent(MainActivity.this,gerenzhongxinActivity.class);
                    startActivity(intent);
                }
                else
                {
                    dialogshow();
                }

            }
        });
        //店铺
        zhuwodianpu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable(MainActivity.this))
                {
                    Intent intent=new Intent(MainActivity.this,MydianpuActivity.class);
                    startActivity(intent);
                }
                else
                {
                    dialogshow();
                }

            }
        });
        //订单
        zhuwodingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable(MainActivity.this))
                {
                    Intent intent=new Intent(MainActivity.this,MydingdanActivity.class);
                    startActivity(intent);
                }
                else
                {
                    dialogshow();
                }

            }
        });
        //分类
        zhuwofenlei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable(MainActivity.this))
                {
                    Intent intent=new Intent(MainActivity.this,fenleiliulanActivity.class);
                    startActivity(intent);
                }
                else
                {
                    dialogshow();
                }

            }
        });
        //热门销售
        zhuworexiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable(MainActivity.this))
                {
                    String searchtiaojian = "4";
                    Intent intent = new Intent(MainActivity.this, rexiaoActivity.class);
                    intent.putExtra("searchtiaojian", searchtiaojian);
                    startActivity(intent);
                }
                else
                {
                    dialogshow();
                }

            }
        });
        //最新
        zhuwozuixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable(MainActivity.this))
                {
                    String searchtiaojian = "5";
                    Intent intent = new Intent(MainActivity.this, zuixinfabuActivity.class);
                    intent.putExtra("searchtiaojian", searchtiaojian);
                    startActivity(intent);
                }
                else
                {
                    dialogshow();
                }

            }
        });
        //攻略
        zhuwogonglue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable(MainActivity.this))
                {
                    Intent intent=new Intent(MainActivity.this,SearchActivity.class);
                    startActivity(intent);
                }
                else
                {
                    dialogshow();
                }

            }
        });




    }
    //listview随scrollview一起滚动的函数
    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    //判断网络是否连接
    public boolean isNetworkAvailable(Activity activity)
    {
        Context context = activity.getApplicationContext();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null)
        {
            return false;
        }
        else
        {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
            if (networkInfo != null && networkInfo.length > 0)
            {
                for (int i = 0; i < networkInfo.length; i++)
                {
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void dialogshow()
    {
        AlertDialog dialog=new AlertDialog.Builder(MainActivity.this).create();
        dialog.setTitle("提示");
        dialog.setMessage("网络未连接，请连接网络.....");
        dialog.setCancelable(false);
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE,"取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.setButton(DialogInterface.BUTTON_POSITIVE,"确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
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

}
