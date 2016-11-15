package com.feicuiedu.android.lrucachedemo;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LruCache<Integer, Bitmap> lruCache = null;
    private List<Integer> list = new ArrayList<Integer>();
    private LayoutInflater lif;
    private BaseAdapter ba = new BaseAdapter() {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Integer getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = null;
            ViewHodler vh = null;
            int resId = getItem(position);

            if (convertView == null) {
                view = lif.inflate(R.layout.activity_main_item, null);
                vh = new ViewHodler();
                vh.iv = (ImageView) view.findViewById(R.id.imgItem);
                view.setTag(vh);
            } else {
                view = convertView;
                vh = (ViewHodler) view.getTag();
            }

            Bitmap bitmap = lruCache.get(resId);
            vh.iv.setImageBitmap(bitmap);

            return view;

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lif = getLayoutInflater();

        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        Log.d("当前的运行内存为:",maxMemory+"KB");

        int cacheSize = maxMemory/8;

        Log.d("设置缓存为:",cacheSize+"KB");
        if (Build.VERSION.SDK_INT >= 12) {
            lruCache = new LruCache<Integer, Bitmap>(cacheSize) {

                @Override
                protected int sizeOf(Integer key, Bitmap value) {
                    Log.d("sizeOf",value.getRowBytes() * value.getHeight()/1024+"KB");
                    return value.getRowBytes() * value.getHeight()/1024;
                }
            };
        } else {
            lruCache = new LruCache<Integer, Bitmap>(cacheSize);
        }

        ListView lv = (ListView) findViewById(R.id.listView1);
        lv.setAdapter(ba);

    }

    private List<Integer> getData() {

        List<Integer> result = new ArrayList<Integer>();

        result.add(getBitmapByResId(R.drawable.img_0094));
        result.add(getBitmapByResId(R.drawable.img_0095));
        result.add(getBitmapByResId(R.drawable.img_0096));
        result.add(getBitmapByResId(R.drawable.img_0097));
        result.add(getBitmapByResId(R.drawable.img_0098));
        result.add(getBitmapByResId(R.drawable.img_0099));
        result.add(getBitmapByResId(R.drawable.img_0100));
        result.add(getBitmapByResId(R.drawable.img_0101));
        result.add(getBitmapByResId(R.drawable.img_0102));
        result.add(getBitmapByResId(R.drawable.img_0103));
        result.add(getBitmapByResId(R.drawable.img_0104));
        result.add(getBitmapByResId(R.drawable.img_0105));
        result.add(getBitmapByResId(R.drawable.img_0106));
        result.add(getBitmapByResId(R.drawable.img_0107));
        result.add(getBitmapByResId(R.drawable.img_0108));

        return result;
    }

    private Integer getBitmapByResId(int resId) {

        Bitmap bitmap = lruCache.get(resId);
        if ( bitmap == null) {

            bitmap = decodeSampledBitmap(getResources(),resId);
            Log.d("当前图片大小为",bitmap.getByteCount()/1024+"KB");
            lruCache.put(resId,bitmap);
            Log.d("getBitmapByResId:"+resId,"lrucache中没有bitmap，需要把它放进去");
        }
        else {
            Log.d("getBitmapByResId:"+resId,"这是从lrucache中取得的bitmap");
        }
        Log.d("lruCache.get("+resId+")",bitmap.toString());
        return resId;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lruCache.size()",lruCache.size()+"");
        list = getData();
        Log.d("lruCache.size()1",lruCache.size()+"");
        ba.notifyDataSetChanged();
    }

    /**
     * 根据传入的图片的resource_id值返回处理后的Bigmap对象
     * @param res
     * @param resId
     * @return
     */
    private Bitmap decodeSampledBitmap(Resources res, int resId) {

        int reqWidth = 100;
        int reqHeight = 100;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inSampleSize = calculateInSample(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    /**
     * 计算图片压缩比例
     * @param options
     * @param reqWidth  压缩后的宽 单位像素
     * @param reqHeight 压缩后的高 单位像素
     * @return
     */
    private int calculateInSample(BitmapFactory.Options options, int reqWidth, int reqHeight) {

        int height = options.outHeight;
        int width = options.outWidth;

        int inSample = 1;

        if (height > reqHeight || width > reqWidth) {

            int halfHeight = height / 2;
            int halfWidth = width / 2;

            while ((halfHeight / inSample) >= reqHeight && (halfWidth / inSample) >= reqWidth) {
                inSample *= 2;
            }
        }

        return inSample;
    }

    private static class ViewHodler {

        ImageView iv;
    }

    public void refresh(View view) {

        this.onResume();
    }
}
