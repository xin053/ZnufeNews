package com.weather.net;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Cui on 16-4-17.
 */
public class GetNetPic {

    private LruCache<String ,Bitmap> mCache;


    public GetNetPic(){

        int maxMemory= (int) Runtime.getRuntime().maxMemory();
        int cacheMemorySize=maxMemory/4;
        mCache=new LruCache<String,Bitmap>(cacheMemorySize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };

    }


    public void addImageToCache(String picUrl,Bitmap bitmap){
        if (getImageFromCache(picUrl) == null) {
            mCache.put(picUrl, bitmap);
        }
    }
    
    public Bitmap getImageFromCache(String picUrl)
    {
        return mCache.get(picUrl);
    }
    
    public void asyLoadImage(final ImageView picImageView,final String picUrl){
        if (getImageFromCache(picUrl) == null) {

            new AsyncTask<Void, Void, Bitmap>() {
                @Override
                protected Bitmap doInBackground(Void... params) {

                    Drawable picResult = loadImageFromNetwork(picUrl);

                    BitmapDrawable bd = (BitmapDrawable) picResult;
                    Bitmap bitmap = bd.getBitmap();

                    addImageToCache(picUrl,bitmap);

                    return bitmap;
                }

                @Override
                protected void onPostExecute(Bitmap bitmap) {
                    if (bitmap != null) {

                        if (picImageView.getTag().equals(picUrl)) {
                            picImageView.setImageBitmap(bitmap);
                        }
                    } else {
                    }
                    super.onPostExecute(bitmap);
                }
            }.execute();
        }else {
            picImageView.setImageBitmap(getImageFromCache(picUrl));
        }
    }
    
    private Drawable loadImageFromNetwork(String urladdr) {
        Drawable drawable = null;
        try{
            //judge if has picture locate or not according to filename
            drawable = Drawable.createFromStream(new URL(urladdr).openStream(), urladdr);
        }catch(IOException e){
            System.out.println("获取图片出现异常");
        }
        if(drawable == null){
            System.out.println("获取图片失败");
        }else{
            System.out.println("获取图片成功");
        }
        return drawable;
    }
}
