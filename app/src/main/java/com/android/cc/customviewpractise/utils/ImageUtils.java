package com.android.cc.customviewpractise.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * author: ChenWei
 * create date: 2017/1/15
 * description:
 */

public class ImageUtils {
    public static Bitmap decodeBmpFromResource(Resources res, int resId, int reqWidth, int reqHeight){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        BitmapFactory.decodeResource(res, resId, options);

        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight){
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;
        Log.e("bmp", "height: " + height);
        Log.e("bmp", "width: " + width);
        Log.e("bmp", "reqIconWidth: " + reqWidth);
        Log.e("bmp", "reqIconheight:  " + reqHeight);
        if(height > reqHeight || width > reqWidth){
            int halfheight = height / 2;
            int halfWidth = width / 2;
            while ((halfheight / inSampleSize >= reqHeight )
                    && (halfWidth / inSampleSize) >= reqWidth){
                inSampleSize *= 2;
            }

        }

        return inSampleSize;
    }


//    public static int[] getBmpSize()
}
