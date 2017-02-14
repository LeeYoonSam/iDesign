package com.bup.idesign.data.remote;

import com.bup.idesign.BuildConfig;
import com.bup.idesign.model.Product;

import java.util.ArrayList;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Albert-IM on 13/02/2017.
 */

public interface RemoteApi {
    final static String RELEASE_SERVER = "http://211.110.1.11/";
    final static String DEBUG_SERVER = "http://211.110.1.11/";

    // 빌드 타입에 따라 BaseURL 가져오기
    class Setting {
        private static String initServerUrl() {
            switch (BuildConfig.DEPLOY_PHASE) {
                case Debug:
                    return DEBUG_SERVER;
                case Release:
                    return RELEASE_SERVER;
                default:
            }

            return null;
        }
    }

    public static final String ENDPOINT = Setting.initServerUrl();

    @GET("main")
    Observable<ArrayList<Product>> getAllProducts();
}
