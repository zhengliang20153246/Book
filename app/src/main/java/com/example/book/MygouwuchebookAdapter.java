package com.example.book;

import android.content.Context;
import android.content.DialogInterface;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by THINK on 2017/10/28.
 */

public class MygouwuchebookAdapter extends ArrayAdapter<Book>
{

    private LruCache<String, BitmapDrawable> mMemoryCache;

    private int resourseId;
    private Context context;
    private static boolean[] checks;//定义数组来保存CheckBox的状态

    public static boolean[] getChecks() {
        return checks;
    }

    public static void setChecks(boolean[] checks) {
        MygouwuchebookAdapter.checks = checks;
    }

    private String[] booknum;//同样定义数组来保存Textview的状态
    private int length;//定义一个长度，来获取listview 的长度
    private ListView mListView;
    private MyClickListener mListener;
    private TextView zongjiatext;
    public MygouwuchebookAdapter(Context context, int textViewResourseId, List<Book> objects,MyClickListener listener,TextView zongjiatext)
    {
        super(context,textViewResourseId,objects);
        resourseId=textViewResourseId;
        this.context=context;
        checks = new boolean[objects.size()];//初始化数组
        booknum=new String[objects.size()];
        length=objects.size();
        for(int i=0;i<objects.size();i++)//这里是我的text的初始化text，为1
        {
            booknum[i]="1";
        }
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String, BitmapDrawable>(cacheSize) {
            @Override
            protected int sizeOf(String key, BitmapDrawable drawable) {
                return drawable.getBitmap().getByteCount();
            }
        };
        mListener = listener;
        this.zongjiatext=zongjiatext;
    }
    @Override
    public View getView(final int position, View  converView, ViewGroup parent)
    {
        if (mListView == null) {
            mListView = (ListView) parent;
        }
        final Book mygouwuchebookclass=getItem(position);
        View view;
        final ViewHolder viewHolder;
        if(converView==null)
        {
            view = LayoutInflater.from(getContext()).inflate(resourseId, parent, false);
            viewHolder=new ViewHolder();
            viewHolder.mygouwuchebookimage=(ImageView) view.findViewById(R.id.mygouwuchebook);
            viewHolder.mygouwuchexuanze=(CheckBox) view.findViewById(R.id.Mygouwuchexuanze);
            viewHolder.mygouwuchebookname=(TextView)view.findViewById(R.id.Mygouwuchebookname);
            viewHolder.mugouwuchebookprice=(TextView)view.findViewById(R.id.Mygouwuchebookprice);
            viewHolder.mygouwuchebooknum=(TextView)view.findViewById(R.id.Mygouwuchebooknum);
            viewHolder.mygouwuchebooknumjian=(Button)view.findViewById(R.id.Mygouwuchebooknumjian);
            viewHolder.mygouwuchebooknumjia=(Button)view.findViewById(R.id.Mygouwuchebooknumjia);
            viewHolder.mygouwuchebookshanchu=(Button)view.findViewById(R.id.Mygouwuchebookshanchu);
            view.setTag(viewHolder);
        }
        else
        {
            view=converView;
            viewHolder=(ViewHolder)view.getTag();
        }
        final int pos  = position;//定义一个final的int类型pos用来记录点击的位置
        viewHolder.mygouwuchexuanze.setFocusable(false);
        viewHolder.mygouwuchebooknumjian.setFocusable(false);
        viewHolder.mygouwuchebooknumjia.setFocusable(false);
        viewHolder.mygouwuchebookshanchu.setFocusable(false);

        //减少数量点击事件
        viewHolder.mygouwuchebooknumjian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewHolder.mygouwuchebooknum.getText().toString().equals("1"))
                {

                }
                else
                {
                    viewHolder.mygouwuchebooknum.setText(String.valueOf(Integer.parseInt(viewHolder.mygouwuchebooknum.getText().toString())-1));
                    booknum[pos]=String.valueOf(Integer.parseInt(viewHolder.mygouwuchebooknum.getText().toString()));
                    //text的text改变时，将text保存进数组里面
                    if(checks[pos]) {
                        double zongjia = Double.parseDouble(String.valueOf(zongjiatext.getText()));
                        zongjia = zongjia - Double.parseDouble(String.valueOf(mygouwuchebookclass.getPrice()));
                        zongjiatext.setText(String.valueOf(zongjia));
                    }
                }


            }
        });
        //增加数量点击事件
        viewHolder.mygouwuchebooknumjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(viewHolder.mygouwuchebooknum.getText().equals(mygouwuchebookclass.getNum()))
                {
                    Toast.makeText(context,"没有更多的数量喽！",Toast.LENGTH_LONG).show();

                }
                else {
                    viewHolder.mygouwuchebooknum.setText(String.valueOf(Integer.parseInt(viewHolder.mygouwuchebooknum.getText().toString()) + 1));
                    booknum[pos] = String.valueOf(Integer.parseInt(viewHolder.mygouwuchebooknum.getText().toString()));

                    if(checks[pos])
                    {
                        double zongjia = Double.parseDouble(String.valueOf(zongjiatext.getText()));
                        zongjia = zongjia +Double.parseDouble(String.valueOf(mygouwuchebookclass.getPrice()));
                        zongjiatext.setText(String.valueOf(zongjia));
                    }

                }
            }
        });

        viewHolder.mygouwuchebookshanchu.setOnClickListener(mListener);
        viewHolder.mygouwuchebookshanchu.setTag(position);



        viewHolder.mygouwuchebookname.setText(mygouwuchebookclass.getBookname());
        viewHolder.mugouwuchebookprice.setText(String.valueOf(mygouwuchebookclass.getPrice()));
        //pos必须声明为final


        //下面是CheckBox的点击事件，
        viewHolder.mygouwuchexuanze.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                checks[pos] = isChecked;
                if(isChecked)
                {
                    double zongjia = Double.parseDouble(String.valueOf(zongjiatext.getText()));
                    //当被选中时，将该位置的CheckBox的状态保存下来
                    zongjia = zongjia+Double.parseDouble(String.valueOf(mygouwuchebookclass.getPrice())) * Double.parseDouble(String.valueOf(viewHolder.mygouwuchebooknum.getText()));
                    zongjiatext.setText(String.valueOf(zongjia));
                }
                else
                {
                    double zongjia = Double.parseDouble(String.valueOf(zongjiatext.getText()));
                    //当被选中时，将该位置的CheckBox的状态保存下来
                    zongjia = zongjia-Double.parseDouble(String.valueOf(mygouwuchebookclass.getPrice())) * Double.parseDouble(String.valueOf(viewHolder.mygouwuchebooknum.getText()));
                    zongjiatext.setText(String.valueOf(zongjia));
                }

            }});


        viewHolder.mygouwuchexuanze.setChecked(checks[pos]);//每次加载这个item时，将CheckBox的状态赋值就OK了
        viewHolder.mygouwuchebooknum.setText(booknum[pos]);//每次加载item时，给TextView的text赋值就OK了
        viewHolder.mygouwuchebookid=mygouwuchebookclass.getId();
        viewHolder.mygouwuchebookname.setText(mygouwuchebookclass.getBookname());
        viewHolder.mugouwuchebookprice.setText(String.valueOf(mygouwuchebookclass.getPrice()));

        BitmapDrawable drawable = getBitmapFromMemoryCache(mygouwuchebookclass.getPicurl1());
       // viewHolder.mygouwuchebookimage.setImageResource(R.drawable.fenlei);
        viewHolder.mygouwuchebookimage.setTag(mygouwuchebookclass.getPicurl1());
        if (drawable != null) {
            viewHolder.mygouwuchebookimage.setImageDrawable(drawable);
        }
        else
        {
            BitmapWorkerTask task = new BitmapWorkerTask(viewHolder.mygouwuchebookimage);
            task.execute(mygouwuchebookclass.getPicurl1());
        }
        return  view;
    }

    public void quanxuan(boolean ischecked)
    {
        for(int i=0;i<length;i++)
        {
            checks[i]=ischecked;
        }
    }


    class ViewHolder
    {
        CheckBox mygouwuchexuanze;
        ImageView mygouwuchebookimage;
        TextView mygouwuchebookname;
        TextView mugouwuchebookprice;
        TextView mygouwuchebooknum;
        Button mygouwuchebooknumjian;
        Button mygouwuchebooknumjia;
        Button mygouwuchebookshanchu;
        int mygouwuchebookid;
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

    public static abstract class MyClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
             myOnClick((Integer) v.getTag(), v);

        }
        public abstract void myOnClick(int position, View v);
    }




}