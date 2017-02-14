package com.bup.idesign.data;

import com.bup.idesign.data.remote.RemoteServices;
import com.bup.idesign.model.Product;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by Albert-IM on 14/02/2017.
 */

public class DataManager {

    private final RemoteServices mRemoteServices;


    public DataManager(RemoteServices remoteServices) {
        mRemoteServices = remoteServices;
    }

    public Observable<ArrayList<Product>> getMainData() {
        return mRemoteServices.getAllProducts();
    }
}
