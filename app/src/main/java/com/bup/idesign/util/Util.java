package com.bup.idesign.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;

import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by Albert-IM on 12/02/2017.
 */

public class Util {

    private static Context mCtx;
    private static Activity mAct;

    public static synchronized void init(Activity act) {
        mCtx = act;
        mAct = act;
    }

    private static Context get() {
        if (mCtx == null) {
            throw new NullPointerException();
        } else {
            return mCtx;
        }
    }

    public static int getWindowHeight() {
        WindowManager manager = (WindowManager) get().getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);

        return size.y;
    }

    public static int getDensityHeight(float height) {

        WindowManager manager = (WindowManager) get().getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);

        float deviceDivision = (dm.densityDpi / 160);
        float newHeight = height * deviceDivision;

        return (int)newHeight;
    }

    public static void setGlideLoadWithSizeWithoutPlaceholder(Context context, String imgPath, int imgWidth, int imgHeight, ImageView ivImage) {
        if(context == null)
            return;

        int sCorner = 30;
        int sMargin = 2;

        Glide.with(context)
                .load(imgPath)
                .override(imgWidth, imgHeight)
                .bitmapTransform(new CropTransformation(context), new RoundedCornersTransformation(context, sCorner, sMargin))
//                .asBitmap()
//                .format(DecodeFormat.PREFER_ARGB_8888)


                .into(ivImage);
    }

    public static void setGlideLoadWithSize(Context context, String imgPath, int imgWidth, int imgHeight, ImageView ivImage) {
        if(context == null)
            return;

        Glide.with(context)
                .load(imgPath)
                .asBitmap()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .override(imgWidth, imgHeight)
                .into(ivImage);
    }

    public static void setGlideLoadWithoutPlaceholder(Context context, String imgPath, ImageView ivImage) {
        if(context == null)
            return;

        Glide.with(context)
                .load(imgPath)
                .asBitmap()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .into(ivImage);
    }

    public static void setGlideLoad(Context context, String imgPath, ImageView ivImage) {
        if(context == null)
            return;

        Glide.with(context)
                .load(imgPath)
                .asBitmap()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .into(ivImage);
    }
}
