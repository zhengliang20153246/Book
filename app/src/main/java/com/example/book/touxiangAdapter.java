package com.example.book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;



/**
 * Created by LI on 2017/10/27.
 */

public class touxiangAdapter extends ArrayAdapter<listtouxiangClass> {
    private int resourceId;
    private Context context;

    public touxiangAdapter(Context context, int textViewResourceId, List<listtouxiangClass> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        listtouxiangClass touxiang = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        ImageView image = (ImageView) view.findViewById(R.id.touxiangimageView);
        TextView name = (TextView) view.findViewById(R.id.touxiangname);
        image.setImageResource(touxiang.getImage1());
        name.setText(touxiang.getName());


        return view;
    }


}
