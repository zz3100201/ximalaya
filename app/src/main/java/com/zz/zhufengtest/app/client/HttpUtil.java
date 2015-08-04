package com.zz.zhufengtest.app.client;

/**
 * Created by zhangzhao11 on 2015/7/28.
 * User:zz
 * Date:2015/7/28
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import com.zz.zhufengtest.app.util.StreamUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.GZIPInputStream;

/**
 * 网络工具类，用语HTTP GET,POST请求
 */
public final class HttpUtil {

    public static final int TIMEOUT_CONNECT = 5000;
    public static final int TIMEOUT_READ = 5000;

    private HttpUtil(){

    }
    /////////////////////////////

    /**
     * 获取GET请求，返回字节数组
     * @param url String url
     * @return byte[]
     */
    public static byte[] doGet(String url){
        byte[] ret = null;
        InputStream in = null;
        if (url != null) {
            //注意释放连接
            HttpURLConnection conn = null;
            try {
                URL u = new URL(url);
                conn = (HttpURLConnection) u.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accpet-Encoding", "gzip");//设置HTTP请求头
                conn.setRequestProperty("User-Agent", "ting_4.1.7(MI2,Android" + Build.VERSION.SDK_INT + ")");
                //设置联网超时，只能够用于短时间的联网操作
                //长时间的自己再重新开发

                //Socket打开连接的时间
                conn.setConnectTimeout(TIMEOUT_CONNECT);
                //打开连接后，开始读数据，可以读取数据多长时间
                conn.setReadTimeout(TIMEOUT_READ);
                /////////////////
                //连接的属性
                //设置自动处理302/307跳转，通常会返回200
                conn.setInstanceFollowRedirects(true);
                conn.connect();
                if (conn.getResponseCode() == 200){
                    in = conn.getInputStream();
                    //获取服务器头信息，内容是否压缩
                    //获取指定的服务器返回的touch信息
                    String contentEncoding = conn.getHeaderField("Content-Encoding");
                    if (contentEncoding == null) {
                        contentEncoding = conn.getHeaderField("Content-encoding");
                    }
                    if (contentEncoding != null&& contentEncoding.equals("gzip")){
                       //代表数据经过压缩
                        //使用GZIPInputStream解压缩
                        in = new GZIPInputStream(in);
                    }
                    //读in
                    ret = StreamUtil.readStream(in);
                }else{
                    //TODO 其他情况
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                StreamUtil.close(in);
                StreamUtil.close(conn);
            }
        }
        return ret;
    }

    /**
     * 获取Bitmap
     * @param url
     * @return
     */
    public static Bitmap doGetBitmap(String url){
        Bitmap ret = null;
        InputStream in = null;
        if (url != null) {
            //注意释放连接
            HttpURLConnection conn = null;
            try {
                URL u = new URL(url);
                conn = (HttpURLConnection) u.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accpet-Encoding", "gzip");//设置HTTP请求头
                conn.setRequestProperty("User-Agent", "ting_4.1.7(MI2,Android" + Build.VERSION.SDK_INT + ")");
                //设置联网超时，只能够用于短时间的联网操作
                //长时间的自己再重新开发

                //Socket打开连接的时间
                conn.setConnectTimeout(TIMEOUT_CONNECT);
                //打开连接后，开始读数据，可以读取数据多长时间
                conn.setReadTimeout(TIMEOUT_READ);
                /////////////////
                //连接的属性
                //设置自动处理302/307跳转，通常会返回200
                conn.setInstanceFollowRedirects(true);
                conn.connect();
                if (conn.getResponseCode() == 200){
                    in = conn.getInputStream();
                    //获取服务器头信息，内容是否压缩
                    //获取指定的服务器返回的touch信息
                    String contentEncoding = conn.getHeaderField("Content-Encoding");
                    if (contentEncoding == null) {
                        contentEncoding = conn.getHeaderField("Content-encoding");
                    }
                    if (contentEncoding != null&& contentEncoding.equals("gzip")){
                        //代表数据经过压缩
                        //使用GZIPInputStream解压缩
                        in = new GZIPInputStream(in);
                    }
                    //读in
                    ret = BitmapFactory.decodeStream(in);
                }else{
                    //TODO 其他情况
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                StreamUtil.close(in);
                StreamUtil.close(conn);
            }
        }
        return ret;
    }
}
