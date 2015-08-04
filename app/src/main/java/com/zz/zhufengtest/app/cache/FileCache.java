package com.zz.zhufengtest.app.cache;

import android.content.ContentValues;
import android.content.Context;
import android.os.Environment;
import com.zz.zhufengtest.app.util.StreamUtil;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zhangzhao11 on 2015/7/31.
 * User:zz
 * Date:2015/7/31
 */
public class FileCache {
    private static FileCache outInstance;
    public static FileCache createInstance(Context context){
        if (context != null) {
            if (outInstance == null){
                outInstance = new FileCache(context);
            }
            return outInstance;
        }else {
            throw new IllegalArgumentException("Context must be set.");
        }

    }
    public static FileCache getInstance(){
        if (outInstance != null) {
            return outInstance;
        }else {
            throw new IllegalStateException("You must invoke createInstance(Context) before getInstance");
        }
    }
    /**
     * 用于获取CacheDir，存储卡上main的
     */
    private Context context;
    private FileCache(Context context){
        this.context = context;
    }
    public byte[] loadFile(String url){
        byte[] ret = null;
        File cacheDir = null;
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)){
            //外部
            cacheDir = context.getExternalCacheDir();
        }else {
            //内部
            cacheDir = context.getCacheDir();
        }
        if (url != null) {
            //TODO  url转换
            String name = md5(url);
            File file = new File(cacheDir,name);
            if (file.exists()){
                FileInputStream fin = null;
                try {
                    fin = new FileInputStream(file);
                    ret = StreamUtil.readStream(fin);
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    StreamUtil.close(fin);
                }
            }
        }
        return ret;
    }
    public void saveFile(String url, byte[] data){
        byte[] ret = null;
        File cacheDir = null;
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)){
            //外部
            cacheDir = context.getExternalCacheDir();
        }else {
            //内部
            cacheDir = context.getCacheDir();
        }
        if (url != null&&data != null) {
            //TODO  url转换
            String name = md5(url);
            File file = new File(cacheDir,name);
            FileOutputStream fout = null;
            try {
                fout = new FileOutputStream(file);
                fout.write(data);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {

                StreamUtil.close(fout);
            }
        }
    }

    /**
     * 转换网址
     * @param url
     * @return
     */
    public static String md5(String url){
        String ret = null;
        if (url != null) {
            try {
                MessageDigest digest = MessageDigest.getInstance("MD5");
                //经过md5转换的内容，字节数组无法new String
                byte[] data = digest.digest(url.getBytes());
                StringBuilder sb = new StringBuilder();
                for (byte b : data) {
                    int h = (b>>4)&0x0f;
                    int l = b & 0x0f;
                    sb.append(Integer.toHexString(h));
                    sb.append(Integer.toHexString(l));
                }
                ret  = sb.toString();
                sb = null;
                data = null;
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
}
