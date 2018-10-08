package com.example.book;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LI on 2017/10/26.
 */

public class gerenzhongxinActivity extends AppCompatActivity implements RewardedVideoAdListener {

    private RewardedVideoAd mAd;
    private List<listgerenzhongxinClass> gerenzhongxinlist = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenzhongxin);

        Myuser.toolbartitle="书籍信息";

        //MobileAds.initialize(getApplicationContext(), "ca-app-pub-8200761898141173~1125089733");
        mAd = MobileAds.getRewardedVideoAdInstance(this);
        mAd.setRewardedVideoAdListener(this);
        //loadRewardedVideoAd();


        //初始化***************
        init();
        //实现跳转***************
        ListView listView2 = (ListView) findViewById(R.id.list_view);
        tiaozhuan(listView2);
        //adapter适配器*************
        gerenzhongxinAdapter adapter = new gerenzhongxinAdapter(gerenzhongxinActivity.this,R.layout.listgerenzhongxin,gerenzhongxinlist);
        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        //返回按钮事件**************
        ImageButton fanhui = (ImageButton) findViewById(R.id.fanhui);
        fanhui.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //finish();
                    Intent i = new Intent(gerenzhongxinActivity.this, gerenzhongxinActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "返回成功！", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        //账户管理事件****************
        Button zhanghuguanli = (Button) findViewById(R.id.zhanghuguanli);
        //给btn1绑定监听事件
        zhanghuguanli.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 给bnt添加点击响应事件
                Intent intent = new Intent(gerenzhongxinActivity.this, zhanghuguanliActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "账户管理>成功！", Toast.LENGTH_SHORT).show();
            }
        });
        //签到按钮事件*****************
        Button qiandao = (Button) findViewById(R.id.qiandao);

        //给btn1绑定监听事件
        qiandao.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 给bnt添加点击响应事件
                if(mAd.isLoaded())
                {
                    mAd.show();
                }
               else
                {
                    Toast.makeText(getApplicationContext(), "签到失败！", Toast.LENGTH_SHORT).show();
                }
              //  Toast.makeText(getApplicationContext(), "签到成功！", Toast.LENGTH_SHORT).show();
            }
        });
    }
//********************************************************

    private void loadRewardedVideoAd() {
        mAd.loadAd("ca-app-pub-8200761898141173/5724123374", new AdRequest.Builder().build());
    }

    // Required to reward the user.
    @Override
    public void onRewarded(RewardItem reward) {
        //Toast.makeText(this, "onRewarded! currency: " + reward.getType() + "  amount: " +
        // reward.getAmount(), Toast.LENGTH_SHORT).show();
        // Reward the user.
    }

    // The following listener methods are optional.
    @Override
    public void onRewardedVideoAdLeftApplication() {
        Toast.makeText(this, "onRewardedVideoAdLeftApplication",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdClosed() {
        Toast.makeText(this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        loadRewardedVideoAd();
         Toast.makeText(this, "onRewardedVideoAdFailedToLoad："+errorCode, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        //Toast.makeText(this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdOpened() {
        Toast.makeText(this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoStarted() {
        Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        mAd.resume(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        mAd.pause(this);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mAd.destroy(this);
        super.onDestroy();
    }

    //********************************************************
    public void tiaozhuan(final ListView listView) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
                if(position==1)
                {
                    Intent i = new Intent(gerenzhongxinActivity.this, rexiaoActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();
                }else if(position==2)
                {
                    Intent i = new Intent(gerenzhongxinActivity.this, fankuiActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_SHORT).show();
                }else if(position==3)
                {
                    Intent i = new Intent(gerenzhongxinActivity.this, xieyiActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "3", Toast.LENGTH_SHORT).show();
                }else if(position==4)
                {
                    Intent i = new Intent(gerenzhongxinActivity.this, teamActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "4", Toast.LENGTH_SHORT).show();
                }else if(position==0)
                {
                    Intent i = new Intent(gerenzhongxinActivity.this, loginActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "0", Toast.LENGTH_SHORT).show();
                }else if(position==5)
                {
                    Intent i = new Intent(gerenzhongxinActivity.this, zuixinfabuActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "5", Toast.LENGTH_SHORT).show();
                } else if(position==6)
                {
                    Myuser.toolbartitle="书籍信息";
                    Intent i = new Intent(gerenzhongxinActivity.this, bookActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "6", Toast.LENGTH_SHORT).show();
                }
                else if(position==7)
                {
                    Intent i = new Intent(gerenzhongxinActivity.this, maijiainfoActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "7", Toast.LENGTH_SHORT).show();
                }

                else if(position==8)
                {
                    Intent i = new Intent(gerenzhongxinActivity.this, fenleiliulanActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "8", Toast.LENGTH_SHORT).show();
                }
                else if(position==9)
                {
                    Intent i = new Intent(gerenzhongxinActivity.this, addbookActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "9", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void init()
    {
        listgerenzhongxinClass yijianfankui = new listgerenzhongxinClass("我的订单",R.drawable.wodedingdan);
        gerenzhongxinlist.add(yijianfankui);

        listgerenzhongxinClass yijianfankui1 = new listgerenzhongxinClass("购物车",R.drawable.gouwuche);
        gerenzhongxinlist.add(yijianfankui1);

        listgerenzhongxinClass yijianfankui2 = new listgerenzhongxinClass("意见反馈",R.drawable.yijianfankui);
        gerenzhongxinlist.add(yijianfankui2);

        listgerenzhongxinClass yijianfankui3 = new listgerenzhongxinClass("用户隐私与协议",R.drawable.yonghuyinsiyuxieyi);
        gerenzhongxinlist.add(yijianfankui3);

        listgerenzhongxinClass yijianfankui4 = new listgerenzhongxinClass("关于我们",R.drawable.guanyuwomen);
        gerenzhongxinlist.add(yijianfankui4);



        listgerenzhongxinClass ttt = new listgerenzhongxinClass("最新发布",R.drawable.yijian);
        gerenzhongxinlist.add(ttt);

        listgerenzhongxinClass sss = new listgerenzhongxinClass("书籍信息",R.drawable.yijian);
        gerenzhongxinlist.add(sss);

        listgerenzhongxinClass mmm = new listgerenzhongxinClass("买家信息",R.drawable.yijian);
        gerenzhongxinlist.add(mmm);

        listgerenzhongxinClass  ddd= new listgerenzhongxinClass("分类浏览",R.drawable.yijian);
        gerenzhongxinlist.add(ddd);

        listgerenzhongxinClass  aaa= new listgerenzhongxinClass("添加图书",R.drawable.yijian);
        gerenzhongxinlist.add(aaa);


    }

}