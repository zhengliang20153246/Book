package com.example.book;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by LI on 2017/10/29.
 */

public class maijiainfoAdapter extends ArrayAdapter<listmaijiainfoClass> {
    private int resourceId;
    private Context context;
    public maijiainfoAdapter(Context context, int textViewResourceId, List<listmaijiainfoClass> objects)
    {
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
        this.context = context;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        listmaijiainfoClass maijiainfo = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView shuinfo = (ImageView) view.findViewById(R.id.shuinfo);
        TextView maijianame = (TextView)  view.findViewById(R.id.maijianame);
        TextView maijiaQQ = (TextView)  view.findViewById(R.id.maijiaQQ);
        TextView maijiaweixin = (TextView)  view.findViewById(R.id.maijiaweixin);
        TextView maijiashouji = (TextView)  view.findViewById(R.id.maijiashouji);

        shuinfo.setImageResource(maijiainfo.getShuinfo());
        maijiashouji.setText(maijiainfo.getMaijiashouji());
        maijianame.setText(maijiainfo.getMaijiainfo());
        maijiaQQ.setText(maijiainfo.getMaijiaQQ());
        maijiaweixin.setText(maijiainfo.getMaijiaweixin());

        Button maijiaqueding=(Button)view.findViewById(R.id.maijiaqueding);
        //给btn1绑定监听事件
        maijiaqueding.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 给bnt1添加点击响应事件
                /*
                AlertDialog.Builder builder= new AlertDialog.Builder(context);//为当前环境

                builder.setIcon(android.R.drawable.ic_dialog_info);//提示图标

                builder.setTitle("提示");//提示框标题

                builder.setMessage("确定此买家？");//提示内容

                builder.setPositiveButton("确认", null);//确定按钮

                builder.create().show();
                */

                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("提示")
                        .setMessage("确定卖给此买家？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(context, "确定" , Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(context, "取消", Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                            }
                        });

                builder.create().show();


                Toast.makeText(context, position+"确定！", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }



}
