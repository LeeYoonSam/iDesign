package com.bup.idesign;

import android.app.Application;

import com.bumptech.glide.Glide;

/**
 * Created by Albert-IM on 12/02/2017.
 */

public class iDesignApplication extends Application {

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(this).clearMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Glide.get(this).trimMemory(level);
    }
    
}
