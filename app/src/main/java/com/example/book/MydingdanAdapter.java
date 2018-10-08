package com.example.book;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

/**
 * Created by THINK on 2017/11/12.
 */

public class MydingdanAdapter extends ArrayAdapter<dingdan>
{
    private LruCache<String, BitmapDrawable> mMemoryCache;
    private int resourseId;
    private Context context;
    private ListView mListView;

    public MydingdanAdapter(Context context, int textViewResourseId, List<dingdan> objects)
    {
        super(context, textViewResourseId, objects);
        resourseId = textViewResourseId;
        this.context = context;
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
    public View getView(final int position, View converView, ViewGroup parent)
    {
        if (mListView == null) {
            mListView = (ListView) parent;
        }
        dingdan mydingdanclass = getItem(position);
        View view;
        final ViewHolder viewHolder;
        if (converView == null)
        {
            view = LayoutInflater.from(getContext()).inflate(resourseId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mydingdanbook = (ImageView) view.findViewById(R.id.Mydingdanbook);
            viewHolder.mydingdanbookname = (TextView) view.findViewById(R.id.Mydingdanbookname);
            viewHolder.mydingdanbookprice = (TextView) view.findViewById(R.id.Mydingdanbookprice);
            viewHolder.mydingdanbookstatus= (TextView) view.findViewById(R.id.Mydingdanstatus);
            view.setTag(viewHolder);
        }
        else
        {
            view = converView;
            viewHolder = (ViewHolder)view.getTag();
        }

        viewHolder.mydingdanbookname.setText(mydingdanclass.getBookname());
        double danprice=Double.parseDouble(mydingdanclass.getBookprice());
        double num=Double.parseDouble(String.valueOf(mydingdanclass.getNum()));
        viewHolder.mydingdanbookprice.setText("¥ "+String.valueOf(danprice*num));
        if(mydingdanclass.getMydingdanbookstatus().equals("1"))
        {
            viewHolder.mydingdanbookstatus.setText("正在交易");
            viewHolder.mydingdanbookstatus.setTextColor(Color.RED);
        }
        else
        {
            viewHolder.mydingdanbookstatus.setText("交易成功");
            viewHolder.mydingdanbookstatus.setTextColor(Color.RED);
        }
        BitmapDrawable drawable = getBitmapFromMemoryCache(mydingdanclass.getBookpicurl());
        //viewHolder.mydingdanbook.setImageResource(R.drawable.fenlei);
        viewHolder.mydingdanbook.setTag(mydingdanclass.getBookpicurl());
        if (drawable != null)
        {
            viewHolder.mydingdanbook.setImageDrawable(drawable);
        }
        else
        {
            BitmapWorkerTask task = new BitmapWorkerTask(viewHolder.mydingdanbook);
            task.execute(mydingdanclass.getBookpicurl());
        }
        return view;
    }

    class ViewHolder {
        ImageView mydingdanbook;
        TextView mydingdanbookname;
        TextView mydingdanbookprice;
        TextView mydingdanbookstatus;
    }

    public void addBitmapToMemoryCache(String key, BitmapDrawable drawable) {
        if (getBitmapFromMemoryCache(key) == null) {
            mMemoryCache.put(key, drawable);
        }
    }

    /*public void chushihua(String bookid)
    {
        BmobQuery<Book> bmobQuery = new BmobQuery<Book>();
        bmobQuery.getObject(bookid, new QueryListener<Book>()
        {
            @Override
            public void done(Book object,BmobException e) {
                if(e==null)
                {
                    bookname=object.getBookname();
                    bookprice=String.valueOf(object.getPrice());
                    bookurl=object.getPic1().getFileUrl();
                }
                else
                {
                    Toast.makeText(context, "书籍查找失败！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }*/

    /**
     * 从LruCache中获取一张图片，如果不存在就返回null。
     *
     * @param key LruCache的键，这里传入图片的URL地址。
     * @return 对应传入键的BitmapDrawable对象，或者null。
     */
    public BitmapDrawable getBitmapFromMemoryCache(String key) {
        return mMemoryCache.get(key);
    }

    class BitmapWorkerTask extends AsyncTask<String, Void, BitmapDrawable> {

        private ImageView mImageView;
        private String imageUrl;

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
        protected void onPostExecute(BitmapDrawable drawable) {
            ImageView imageView = (ImageView) mListView.findViewWithTag(imageUrl);
            if (imageView != null && drawable != null) {
                imageView.setImageDrawable(drawable);
            }
        }

        /**
         * 建立HTTP请求，并获取Bitmap对象。
         *
         * @param imageUrl 图片的URL地址
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

