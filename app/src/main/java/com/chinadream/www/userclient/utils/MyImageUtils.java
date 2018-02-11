package com.chinadream.www.userclient.utils;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.widget.ImageView;
import android.widget.Toast;

import com.chinadream.www.userclient.R;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

public class MyImageUtils {

    Context context;

    public MyImageUtils(Context context) {
        this.context=context;
    }


    /**
     * 加载图片的方式
     * @param imageView 组件
     * @param url 图片地址，可以是本地，可以是网络的
     * @param demins1 图片尺寸1
     * @param demins2 图片尺寸2
     * @param radius 图片圆角
     * @param defaultId 默认图片ID
     */
    public void loadImageView(ImageView imageView,String url,int demins1,
                              int demins2,int radius,int defaultId){
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(demins1), DensityUtil.dip2px(demins2))//图片大小
                .setRadius(DensityUtil.dip2px(radius))//ImageView圆角半径
                .setCrop(true)// 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(defaultId)//加载中默认显示图片
                .setFailureDrawableId(defaultId)//加载失败后默认显示图片
                .build();
        x.image().bind(imageView, url,imageOptions);
    }

    //处理相册数据
    public String initAlbumData(Intent data) {
        Uri selectedImage = data.getData();
        String[] filePathColumn = { MediaStore.Images.Media.DATA };

        Cursor cursor = context.getContentResolver().query(selectedImage,
                filePathColumn, null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
//        Toast.makeText(context, picturePath, Toast.LENGTH_LONG).show();
        cursor.close();
        return picturePath;
    }

    //处理拍照数据
    public String initCameraData(Intent data) {
        Bitmap bitmap=null;
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            Toast.makeText(context,"内存不足",Toast.LENGTH_SHORT).show();
            return null;
        }
        String name = new DateFormat().format("yyyyMMdd_hhmmss",
                Calendar.getInstance(Locale.CHINA)) + ".jpg";

        Bundle bundle = data.getExtras();
        bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
        FileOutputStream b = null;
        File file = new File("/sdcard/myImage/");
        if (!file.exists())
            file.mkdirs();// 创建文件夹
        String fileName = file.getAbsolutePath()+name;
//        Toast.makeText(context, fileName, Toast.LENGTH_LONG).show();
        try {
            b = new FileOutputStream(fileName);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }

}
