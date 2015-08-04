package com.zz.zhufengtest.app.cache;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.util.LruCache;
import com.zz.zhufengtest.app.util.MyLog;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by zhangzhao11 on 2015/8/1.
 * User:zz
 * Date:2015/8/1
 */
public class MemeryCache {
    private static MemeryCache ourInstance;

    public static MemeryCache getInstance() {
        if (ourInstance == null) {
            ourInstance = new MemeryCache();
        }
        return ourInstance;
    }

    /**
     * 使用了LRU算法的，并且可以指定内存最大限制的 图片缓存<br/>
     * 相当于一个HashMap
     */
    private LruCache<String,Bitmap> lruCache;
    private HashMap<String,SoftReference<Bitmap>> softCache;

    private MemeryCache() {
        //设置内存的尺寸，通常都是最大内存数/8
        int memorySize = (int) (Runtime.getRuntime().maxMemory() / 8);
        //使用内存字节数的情况，需要重写sizeOf方法
        lruCache = new LruCache<String, Bitmap>(memorySize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                int ret = 0;
                if (Build.VERSION.SDK_INT >= 19){
                    ret = value.getAllocationByteCount();
                }else if (Build.VERSION.SDK_INT >=12){
                    ret = value.getByteCount();
                }else {
                    //对于低于12的版本，需要计算width*height*颜色字节数
                    //一行像素所占用的字节数
                    int rowBytes = value.getRowBytes();
                    int bitmapHeight = value.getHeight();
                    ret = rowBytes*bitmapHeight;
                }
                return ret;
            }
        };
        softCache = new LinkedHashMap<String, SoftReference<Bitmap>>();
    }

    /**
     * 获取缓存的图片
     * @param url
     * @return
     */
    public Bitmap getBitmap(String url){
        Bitmap ret = null;
        if (url != null) {
            ret = lruCache.get(url);
            if (ret == null) {
                SoftReference<Bitmap> reference = softCache.get(url);
                if (reference != null) {
                    //获取软引用指向的数据
                    ret = reference.get();
                    if (ret != null) {
                        lruCache.put(url,ret);
                    }
                }
            }
        }
        return  ret;
    }

    public void putBitmap(String url,Bitmap bitmap){
        if (url != null && bitmap != null) {
            //更新LruCache
            lruCache.put(url,bitmap);
            //更新软引用缓存
            softCache.put(url,new SoftReference<Bitmap>(bitmap));
            MyLog.d("Memory","lru size"+lruCache.size());
        }
    }
}
