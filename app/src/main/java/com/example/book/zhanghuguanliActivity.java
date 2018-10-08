package com.example.book;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LI on 2017/10/27.
 */

public class zhanghuguanliActivity extends AppCompatActivity {

    public static final int CHOOSE_PHOTO = 2;
    private List<listzhanghuguanliClass> zhanghuguanlilist = new ArrayList<>();
    private List<listtouxiangClass> touxianglist = new ArrayList<>();
    private ImageView picture;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhanghuguanli);

        Myuser.toolbartitle="书籍信息";

        //初始化
        init();
        //init2();
        //adapter适配器*************
        zhanghuguanliAdapter adapter = new zhanghuguanliAdapter(zhanghuguanliActivity.this,R.layout.listzhanghuguanli,zhanghuguanlilist);
        ListView listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);
        //adapter适配器*************
        /*
        touxiangAdapter adapter2 = new touxiangAdapter(zhanghuguanliActivity.this,R.layout.listtouxiang,touxianglist);
        ListView listView3 = (ListView)findViewById(R.id.listview3);
        listView3.setAdapter(adapter2);
        */
        //实现跳转***************
        ListView listView2 = (ListView) findViewById(R.id.listview);
       // ListView listView33 = (ListView) findViewById(R.id.listview3);
        tiaozhuan(listView2);
       // tiaozhuan2(listView33);

        //返回按钮事件**************
        ImageButton fanhui = (ImageButton) findViewById(R.id.fanhui);
        fanhui.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //finish();
                    Intent i = new Intent(zhanghuguanliActivity.this, gerenzhongxinActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "返回成功！", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        //退出登录
        Button tuichu=(Button)findViewById(R.id.tuichu);
        //给btn1绑定监听事件
        tuichu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // 给bnt1添加点击响应事件
                Toast.makeText(getApplicationContext(), "提交成功！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(zhanghuguanliActivity.this, loginActivity.class);
                //启动
                startActivity(intent);
                finish();

            }
        });

        //头像
        picture = (ImageView) findViewById(R.id.touxiang) ;
        picture.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                if(ContextCompat.checkSelfPermission(zhanghuguanliActivity.this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(zhanghuguanliActivity.this,new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    openAlbum();
                }
            }
        });

    }
    //打开相册
    private void openAlbum(){
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults){
        switch(requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }else{
                    Toast.makeText(this,"你拒绝了权限访问！",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        switch (requestCode){
            case CHOOSE_PHOTO:
                if(resultCode == RESULT_OK){
                    if(Build.VERSION.SDK_INT>=19){
                        handleImageOnKitKat(data);
                    }else{
                        handleImageBeforeKitKat(data);
                    }
                }
        }
    }
    @TargetApi(19)
    private void handleImageOnKitKat(Intent data){
        String imagePath = null;
        Uri uri = data.getData();
        if(DocumentsContract.isDocumentUri(this,uri)){
            //如果dcoument类型是U日，通过document的id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = docId.split(":")[1];//解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath = getImagePath(contentUri,null);
            }
        }else if("content".equalsIgnoreCase(uri.getScheme())){
            //普通方式处理，ifcontent类型是Uri
            imagePath= getImagePath(uri,null);
        }else if("file".equalsIgnoreCase(uri.getScheme())){
            imagePath = uri.getPath();
        }
        displayImage(imagePath);
    }

    private void handleImageBeforeKitKat(Intent data){
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri,String selection){
        String path = null;
        //selection获得图片真实路径
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if(cursor !=null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath){
        if(imagePath != null){
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            picture.setImageBitmap(bitmap);
        }else{
            Toast.makeText(this,"failed to get image!",Toast.LENGTH_SHORT).show();
        }
    }
    //初始化
    private void init()
    {
        listzhanghuguanliClass nicheng = new listzhanghuguanliClass("昵称","我是昵称");
        zhanghuguanlilist.add(nicheng);

        listzhanghuguanliClass sex= new listzhanghuguanliClass("性别","我是性别");
        zhanghuguanlilist.add(sex);

        listzhanghuguanliClass email = new listzhanghuguanliClass("邮箱","我是邮箱");
        zhanghuguanlilist.add(email);

        listzhanghuguanliClass qq = new listzhanghuguanliClass("QQ","我是QQ");
        zhanghuguanlilist.add(qq);

        listzhanghuguanliClass weixin = new listzhanghuguanliClass("微信","我是微信");
        zhanghuguanlilist.add(weixin);

        listtouxiangClass touxiang = new listtouxiangClass("头像",R.drawable.touxiang);
        touxianglist.add(touxiang);
    }
/*
    private void init2()
    {
        listtouxiangClass touxiang = new listtouxiangClass("头像",R.drawable.touxiang);
        touxianglist.add(touxiang);
    }
*/
    public void tiaozhuan(ListView listView) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
                if(position==1)
                {
                    Intent i = new Intent(zhanghuguanliActivity.this, genggaisexActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();
                }else if(position==4)
                {
                    Intent i = new Intent(zhanghuguanliActivity.this, genggaiweixinActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "4", Toast.LENGTH_SHORT).show();
                }else if(position==2)
                {
                    Intent i = new Intent(zhanghuguanliActivity.this, genggaiemailActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_SHORT).show();
                }else if(position==3)
                {
                    Intent i = new Intent(zhanghuguanliActivity.this, genggaiqqActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "3", Toast.LENGTH_SHORT).show();
                }else if(position==0)
                {
                    Intent i = new Intent(zhanghuguanliActivity.this, genggainameActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "0", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
/*
    public void tiaozhuan2(final ListView listView) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
          if(position==0)
                {

                    Toast.makeText(getApplicationContext(), "头像0", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    */

}