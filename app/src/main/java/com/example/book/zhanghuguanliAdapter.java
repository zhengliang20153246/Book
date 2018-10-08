package com.example.book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by LI on 2017/10/27.
 */

public class zhanghuguanliAdapter extends ArrayAdapter<listzhanghuguanliClass> {
    private int resourceId;
    public zhanghuguanliAdapter(Context context, int textViewResourceId, List<listzhanghuguanliClass> objects)
    {
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        listzhanghuguanliClass zhanghuguanli = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView name1 = (TextView)  view.findViewById(R.id.textView1);
        TextView name2 = (TextView)  view.findViewById(R.id.textView2);
        name1.setText(zhanghuguanli.getName1());
        name2.setText(zhanghuguanli.getName2());
        return view;
    }
}
