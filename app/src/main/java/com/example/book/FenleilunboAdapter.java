package com.example.book;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.adapter.StaticPagerAdapter;

/**
 * Created by THINK on 2017/11/12.
 */

public class FenleilunboAdapter extends StaticPagerAdapter
{
    private int[] imgs = {
            R.drawable.a1,
            R.drawable.a3,
            R.drawable.c,
            R.drawable.a4,
    };
    private int[] imag1=new int[3];


    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        view.setImageResource(imgs[position]);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }


    @Override
    public int getCount() {
        return imgs.length;
    }
}
