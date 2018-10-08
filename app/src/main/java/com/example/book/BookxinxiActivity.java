package com.example.book;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.view.ViewGroup.LayoutParams;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.io.InputStream;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;


public class BookxinxiActivity extends AppCompatActivity {

    //统计下载了几张图片
    int n=0;
    //统计当前viewpager轮播到第几页
    int p=0;
    private ViewPager vp;
    //准备好2张网络图片的地址
    private String imageUrl[]=new String[2];

    //装载下载图片的集合
    //装载下载图片的集合
    private List<ImageView> data;
    //控制图片是否开始轮播的开关,默认关的
    private boolean isStart=false;
    //开始图片轮播的线程
    private MyThread t;
    //存放代表viewpager播到第几张的小圆点
    private LinearLayout ll_tag;
    //存储小圆点的一维数组
    private ImageView tag[];


    Bitmap bitmap;
    ImageView imageview;
    private String bookid;
    private RollPagerView mRollViewPager;

    private Handler mHandler=new Handler(){
        public void handleMessage(android.os.Message msg) {
            switch(msg.what){
                case 0:
                    n++;
                    Bitmap bitmap=(Bitmap) msg.obj;
                    ImageView iv=new ImageView(BookxinxiActivity.this);
                    iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    iv.setImageBitmap(bitmap);
                    //把图片添加到集合里
                    data.add(iv);
                    //当接收到第三张图片的时候，设置适配器,
                    if(n==imageUrl.length){
                        vp.setAdapter(new LunboBookxinxiAdapter(data,BookxinxiActivity.this));
                        //创建小圆点
                        creatTag();
                        //把开关打开
                        isStart=true;
                        t=new MyThread();
                        //启动轮播图片线程
                        t.start();

                    }
                    break;
                case 1:
                    //接受到的线程发过来的p数字
                    int page=(Integer) msg.obj;
                    vp.setCurrentItem(page);

                    break;

            }
        };
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookxinxi);

        Toolbar toolbar = (Toolbar) findViewById(R.id.Bookxinxitoolbar);
        toolbar.setTitle("");
        toolbar.inflateMenu(R.menu.mune_bookxinxi);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        bookid = intent.getStringExtra("bookid");
        BmobQuery<Book> query = new BmobQuery<Book>();
        query.getObject(bookid, new QueryListener<Book>() {

            @Override
            public void done(Book object, BmobException e) {
                if (e == null) {
                    imageUrl[0] = object.getPic1().getFileUrl();
                    imageUrl[1] = object.getPic2().getFileUrl();
                    init();
                    //获得playerName的信息
                    //object.getPlayerName();
                    //获得数据的objectId信息
                    //object.getObjectId();
                    //获得createdAt数据创建时间（注意是：createdAt，不是createAt）
                    //object.getCreatedAt();
                } else {
                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
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
                Toast.makeText(BookxinxiActivity.this, "收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Bookxinxigeren:
                Toast.makeText(BookxinxiActivity.this, "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Bookxinximydianpu:
                Toast.makeText(BookxinxiActivity.this, "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Bookxinxigouwuche:
                Toast.makeText(BookxinxiActivity.this, "字号", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Bookxinxidingdan:
                Toast.makeText(BookxinxiActivity.this, "模式", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        // TODO Auto-generated method stub
        vp=(ViewPager) findViewById(R.id.vp);
        ll_tag=(LinearLayout) findViewById(R.id.ll_tag);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                //把当前的页数赋值给P
                p=position;
                //得到当前图片的索引,如果图片只有三张，那么只有0，1，2这三种情况
                int currentIndex=(position%imageUrl.length);
                for(int i=0;i<tag.length;i++){
                    if(i==currentIndex){
                        tag[i].setBackgroundResource(R.drawable.dian1);
                    }else{
                        tag[i].setBackgroundResource(R.drawable.dian2);
                    }
                }

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //这个switch语句我注掉了，我觉得这个语句没有问题啊，可是为什么加上以下语句，当用手拉一次viewpager的时候，轮播就停止了，再也恢复不过来了?有人知道吗
                //switch(state){
                //当页面被手指拉动的时候，暂停轮播
                //case ViewPager.SCROLL_STATE_DRAGGING:
                //  isStart=false;
                //  break;
                //当手指拉完松开或者页面自己翻到下一页静止的时候,开始轮播
                //case ViewPager.SCROLL_STATE_IDLE:
                //  isStart=true;

                //  break;
                //
                //}
            }
        });
        //构造一个存储照片的集合
        data=new ArrayList<ImageView>();
        //从网络上把图片下载下来
        for(int i=0;i<imageUrl.length;i++){
            getImageFromNet(imageUrl[i]);

        }




    }
    private void getImageFromNet(final String imagePath) {
        // TODO Auto-generated method stub
        new Thread(){
            public void run() {
                try {
                    URL url=new URL(imagePath);
                    HttpURLConnection con=(HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setConnectTimeout(10*1000);
                    InputStream is=con.getInputStream();
                    //把流转换为bitmap
                    Bitmap bitmap=BitmapFactory.decodeStream(is);
                    Message message=new Message();
                    message.what=0;
                    message.obj=bitmap;
                    //把这个bitmap发送到hanlder那里去处理
                    mHandler.sendMessage(message);

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            };
        }.start();

    }
    //控制图片轮播
    class MyThread extends Thread{
        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            while(isStart){
                Message message=new Message();
                message.what=1;
                message.obj=p;
                mHandler.sendMessage(message);
                try {
                    //睡眠3秒,在isStart为真的情况下，一直每隔三秒循环
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                p++;
            }
        }
    }
    protected void creatTag() {
        tag=new ImageView[imageUrl.length];
        for(int i=0;i<imageUrl.length;i++){

            tag[i]=new ImageView(BookxinxiActivity.this);
            //第一张图片画的小圆点是白点
            if(i==0){
                tag[i].setBackgroundResource(R.drawable.dian1);
            }else{
                //其它的画灰点
                tag[i].setBackgroundResource(R.drawable.dian2);
            }
            //设置上下左右的间隔
            tag[i].setPadding(10, 20, 10, 20);
            tag[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            //添加到viewpager底部的线性布局里面
            ll_tag.addView(tag[i]);
        }

    }

}
