package com.example.book;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by THINK on 2017/10/28.
 */

public class MydianpubookAdapter extends ArrayAdapter<Book>
{
    private int resourseId;
    private Context context;
    private LruCache<String, BitmapDrawable> mMemoryCache;
    private ListView mListView;
    public MydianpubookAdapter(Context context, int textViewResourseId, List<Book> objects)
    {
        super(context,textViewResourseId,objects);
        resourseId=textViewResourseId;
        this.context=context;
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String, BitmapDrawable>(cacheSize) {
            @Override
            protected int sizeOf(String key, BitmapDrawable drawable) {
                return drawable.getBitmap().getByteCount();
            }
        };
    }
    @Override
    public View getView(final int position, View  converView, ViewGroup parent)
    {
        if (mListView == null) {
            mListView = (ListView) parent;
        }
        Book mydianpubookclass=getItem(position);
        View view;
        ViewHolder viewHolder;
        if(converView==null)
        {
            view=LayoutInflater.from(getContext()).inflate(resourseId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.mydianpubook=(ImageView)view.findViewById(R.id.mydianpubook);
            viewHolder.mydianpubookname=(TextView) view.findViewById(R.id.Mydianpubookname);
            viewHolder.mydianpubookprice=(TextView) view.findViewById(R.id.Mydianpubookprice);
            viewHolder.mydianpubinaji=(Button)view.findViewById(R.id.Mydianpubianji);
            viewHolder.mydianpuchakan=(Button)view.findViewById(R.id.Mydianpuchakan);
            view.setTag(viewHolder);
        }
        else
        {
            view=converView;
            viewHolder=(ViewHolder)view.getTag();
        }
        viewHolder.mydianpubinaji.setFocusable(false);
        viewHolder.mydianpuchakan.setFocusable(false);
        viewHolder.mydianpubinaji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,position+"编辑",Toast.LENGTH_LONG).show();
            }
        });

        viewHolder.mydianpuchakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,position+"查看",Toast.LENGTH_LONG).show();
            }
        });
        viewHolder.mydianpubookname.setText(mydianpubookclass.getBookname());
        viewHolder.mydianpubookprice.setText(String.valueOf(mydianpubookclass.getPrice())+"¥");
        viewHolder.mudianpubookid=mydianpubookclass.getId();
        BitmapDrawable drawable = getBitmapFromMemoryCache(mydianpubookclass.getPicurl1());
        // viewHolder.mygouwuchebookimage.setImageResource(R.drawable.fenlei);
         viewHolder.mydianpubook.setTag(mydianpubookclass.getPicurl1());
        if (drawable != null) {
            viewHolder.mydianpubook.setImageDrawable(drawable);
        }
        else
        {
            BitmapWorkerTask task = new BitmapWorkerTask(viewHolder.mydianpubook);
            task.execute(mydianpubookclass.getPicurl1());
        }
        return  view;
    }
    class ViewHolder
    {
        ImageView mydianpubook;
        TextView mydianpubookname;
        TextView mydianpubookprice;
        Button mydianpubinaji;
        Button mydianpuchakan;
        int mudianpubookid;
    }
    public void addBitmapToMemoryCache(String key, BitmapDrawable drawable) {
        if (getBitmapFromMemoryCache(key) == null) {
            mMemoryCache.put(key, drawable);
        }
    }

    /**
     * 从LruCache中获取一张图片，如果不存在就返回null。
     *
     * @param key
     *            LruCache的键，这里传入图片的URL地址。
     * @return 对应传入键的BitmapDrawable对象，或者null。
     */
    public BitmapDrawable getBitmapFromMemoryCache(String key) {
        return mMemoryCache.get(key);
    }

    class BitmapWorkerTask extends AsyncTask<String, Void, BitmapDrawable> {

        private ImageView mImageView;
        private  String imageUrl;

        public BitmapWorkerTask(ImageView imageView) {
            mImageView = imageView;
        }

        @Override
        protected BitmapDrawable doInBackground(String... params) {
            imageUrl = params[0];
            // 在后台开始下载图片
            Bitmap bitmap = downloadBitmap(imageUrl);
            BitmapDrawable drawable = new BitmapDrawable(getContext().getResources(), bitmap);
            addBitmapToMemoryCache(imageUrl, drawable);
            return drawable;
        }

        @Override
        protected void onPostExecute(BitmapDrawable drawable)
        {
            ImageView imageView = (ImageView) mListView.findViewWithTag(imageUrl);
            if (imageView != null && drawable != null) {
                imageView.setImageDrawable(drawable);
            }
        }

        /**
         * 建立HTTP请求，并获取Bitmap对象。
         *
         * @param imageUrl
         *            图片的URL地址
         * @return 解析后的Bitmap对象
         */
        private Bitmap downloadBitmap(String imageUrl) {
            Bitmap bitmap = null;
            HttpURLConnection con = null;
            try {
                URL url = new URL(imageUrl);
                con = (HttpURLConnection) url.openConnection();
                con.setConnectTimeout(5 * 1000);
                con.setReadTimeout(10 * 1000);
                bitmap = BitmapFactory.decodeStream(con.getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (con != null) {
                    con.disconnect();
                }
            }
            return bitmap;
        }

    }
}