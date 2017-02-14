package com.bup.idesign.data.remote;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.bup.idesign.data.ProductDataSource;
import com.bup.idesign.model.Product;

import java.util.ArrayList;

import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by Albert-IM on 13/02/2017.
 */

public class ProductRemoteDataSource implements ProductDataSource {

    private static ProductRemoteDataSource INSTANCE;

    private static final int SERVICE_LATENCY_IN_MILLIS = 2000;

    private final RemoteApi remoteApi;

    public static ProductRemoteDataSource getInstance(RemoteApi remoteApi) {
        if (INSTANCE == null) {
            INSTANCE = new ProductRemoteDataSource(remoteApi);
        }
        return INSTANCE;
    }

    // Prevent direct instantiation.
    private ProductRemoteDataSource(RemoteApi remoteApi) {
        this.remoteApi = remoteApi;
    }

    @Override
    public void getProducts(final @NonNull LoadProductsCallback callback) {
        // Simulate network by delaying the execution.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                remoteApi.getAllProducts()
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Subscriber<ArrayList<Product>>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(ArrayList<Product> products) {
                                callback.onProductsLoaded(products);
                            }
                        });

//                callback.onProductsLoaded(remoteApi.getAllProducts());
            }
        }, SERVICE_LATENCY_IN_MILLIS);
    }

    @Override
    public void getProduct(@NonNull String productSeq, final @NonNull GetProductCallback callback) {
//        final Product product = TASKS_SERVICE_DATA.get(productSeq);
//
//        // Simulate network by delaying the execution.
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                callback.onProductLoaded(product);
//            }
//        }, SERVICE_LATENCY_IN_MILLIS);
    }

    @Override
    public void refreshProducts() {

    }
}
