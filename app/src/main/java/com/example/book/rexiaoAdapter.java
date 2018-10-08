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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by LI on 2017/10/28.
 */

public class rexiaoAdapter extends ArrayAdapter<Book> {
    private LruCache<String, BitmapDrawable> mMemoryCache;
    private int resourceId;
    private Context context;
    public rexiaoAdapter(Context context, int textViewResourceId, List<Book> objects)
    {
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
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
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Book rexiao = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null)
        {
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.image = (ImageView) view.findViewById(R.id.rexiaoimage);
            viewHolder.name = (TextView)  view.findViewById(R.id.rexiaoname);
            viewHolder.price = (TextView)  view.findViewById(R.id.rexiaoprice);
            viewHolder.date = (TextView)  view.findViewById(R.id.rexiaodate);
            view.setTag(viewHolder);
        }
        else
        {
            view =convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.bookid=rexiao.getId();
        viewHolder.name.setText(rexiao.getBookname());
        viewHolder.price.setText(String.valueOf(rexiao.getPrice())+"¥");
        viewHolder.date.setText(String.valueOf(rexiao.getDate()));
        BitmapDrawable drawable = getBitmapFromMemoryCache(rexiao.getPicurl1());

        if (drawable != null) {
            viewHolder.image.setImageDrawable(drawable);
        }
        else
        {
            BitmapWorkerTask task = new BitmapWorkerTask(viewHolder.image);
            task.execute(rexiao.getPicurl1());
        }
        return view;
        /*
        listrexiaoClass rexiao = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView image = (ImageView) view.findViewById(R.id.rexiaoimage);
        TextView name = (TextView)  view.findViewById(R.id.rexiaoname);
        TextView price = (TextView)  view.findViewById(R.id.rexiaoprice);
        TextView date = (TextView)  view.findViewById(R.id.rexiaodate);

        image.setImageResource(rexiao.getImage1());
        name.setText(rexiao.getName());
        price.setText(rexiao.getPrice());
        date.setText(rexiao.getDate());
        return view;
        */

    }
    class ViewHolder {
        ImageView image;
        TextView name;
        TextView price;
        TextView date;
        int bookid;
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
            if (mImageView != null && drawable != null) {
                mImageView.setImageDrawable(drawable);
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
