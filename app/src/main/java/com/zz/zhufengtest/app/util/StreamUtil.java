package com.zz.zhufengtest.app.util;

/**
 * Created by zhangzhao11 on 2015/7/28.
 * User:zz
 * Date:2015/7/28
 */

import java.io.*;
import java.net.HttpURLConnection;

/**
 * IO流操作的工具类
 */
public class StreamUtil {
    private StreamUtil(){

    }
    public static void close(Object stream){
        if (stream != null) {
            try {
                if(stream instanceof  InputStream){
                    ((InputStream)stream).close();
                }else if (stream instanceof OutputStream){
                    ((OutputStream)stream).close();
                }else if (stream instanceof Reader){
                    ((Reader)stream).close();
                }else if (stream instanceof Writer){
                    ((Writer)stream).close();
                }else if (stream instanceof HttpURLConnection){
                    ((HttpURLConnection)stream).disconnect();
                }
            }catch (Exception ex){

            }
        }
    }

    /**
     * 将输入流中的数据，读出来存储在字节数组中
     * @param in
     * @return
     */
    public  static byte[] readStream(InputStream in) throws IOException {
        byte[] ret = null;
        if (in != null) {
            byte[] buf = new byte[1024];
            int len;
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            while((len = in.read(buf))!=-1){
                bout.write(buf,0,len);
            }
            ret  = bout.toByteArray();
        }
        return ret;
    }
}
