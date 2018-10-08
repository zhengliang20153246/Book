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
 * Created by LI on 2017/10/26.
 */

public class gerenzhongxinAdapter extends ArrayAdapter<listgerenzhongxinClass>{
    private int resourceId;
    public gerenzhongxinAdapter(Context context, int textViewResourceId, List<listgerenzhongxinClass> objects)
    {
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        listgerenzhongxinClass gerenzhongnxin = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView image = (ImageView) view.findViewById(R.id.gerenzhongxinimage1);
        TextView name = (TextView)  view.findViewById(R.id.gerenzhongxinname);
        image.setImageResource(gerenzhongnxin.getImageid());
        name.setText(gerenzhongnxin.getName());
        return view;
    }
}
