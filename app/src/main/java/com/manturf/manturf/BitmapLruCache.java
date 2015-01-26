package com.manturf.manturf;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;


public class BitmapLruCache extends LruCache<String, Bitmap> implements ImageLoader.ImageCache{

    static int getMacCacheSize(){
        int maxMemory = (int)(Runtime.getRuntime().maxMemory()/1014);
        return maxMemory / 8;
    }

    public BitmapLruCache(){
        super(getMacCacheSize());
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getRowBytes()*value.getHeight();
    }

    @Override
    public Bitmap getBitmap(String url) {
        return get(url);
    }


    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        put(url, bitmap);
    }

}
