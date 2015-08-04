package com.zz.zhufengtest.app.Tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import com.zz.zhufengtest.app.cache.FileCache;
import com.zz.zhufengtest.app.cache.MemeryCache;
import com.zz.zhufengtest.app.client.HttpUtil;
import com.zz.zhufengtest.app.util.ImageUtil;

/**
 * Created by zhangzhao11 on 2015/7/31.
 * User:zz
 * Date:2015/7/31
 */

/**
 * 下载图片
 */
public class ImageLoadTask extends AsyncTask<String,Integer,Bitmap> {
    //当前任务要设置的目标
    private ImageView imageView;
    private String url;

    public ImageLoadTask(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap ret = null;
        if (params != null && params.length >0){
            url = params[0];
            //1.从内存缓存获取图片Bitmap
            ret = MemeryCache.getInstance().getBitmap(url);
            //获取到，返回，没有获取到，直接进入文件缓存步骤
            if (ret == null) {
                byte[] data = FileCache.getInstance().loadFile(url);
                if (data == null) {
                    data = HttpUtil.doGet(url);
                    //存文件
                    FileCache.getInstance().saveFile(url,data);
                }

                //1.内存缓存
                //1.文件缓存
                //2.图片二次采样
                if (data != null){
                    //TODO 进行二次采样
                    //1.只获取尺寸
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    //如果该值设为true那么将不返回实际的bitmap，也不给其分配内存空间这样就避免内存溢出了。但是允许我们查询图片的信息这其中就包括图片大小信息
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeByteArray(data, 0, data.length, options);
                    //2.计算原始尺寸与目标尺寸的采样比率
//                    options.inPurgeable = true;
                    options.inSampleSize = ImageUtil.calculateInSampleSize(options,imageView.getWidth(),imageView.getHeight());
                    //3.设置Options为实际解析图片，并且设置采样比率，能够缩小了
                    options.inJustDecodeBounds = false;
                    //写2的整数倍，因为这样速度最快。如果不是2的整数倍，会自动向下转换到最近的2的整数倍。
//                    options.inSampleSize = 2;
                    //设置解码器可以使用的解码像素颜色的配置
                    //注意透明度与颜色的关系
                    //如果图片不能够使用这个配置，那么就自动地使用ARGB_8888
                    //通过设置Options.inPreferredConfig值来降低内存消耗：
                    //默认为ARGB_8888: 每个像素4字节. 共32位。
                    //Alpha_8: 只保存透明度，共8位，1字节。
                    //ARGB_4444: 共16位，2字节。
                    //RGB_565:共16位，2字节。
                    //如果不需要透明度，可把默认值ARGB_8888改为RGB_565,节约一半内存
                    options.inPreferredConfig = Bitmap.Config.RGB_565;


                    ret = BitmapFactory.decodeByteArray(data,0,data.length,options);
                    MemeryCache.getInstance().putBitmap(url,ret);
                    data = null;
                }
            }


        }
        return ret;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (bitmap != null) {
            if (imageView != null) {
                Object tag = imageView.getTag();
                if (tag != null) {
                    String str = null;
                    if (tag instanceof String){
                        str = (String) tag;
                    }else if (tag instanceof  String[]){
                        String[] ss = (String[]) tag;
                        str = ss[0];
                    }
                    if (str.equals(url)){
                        imageView.setImageBitmap(bitmap);
                    }
                }
            }
        }
    }
}
