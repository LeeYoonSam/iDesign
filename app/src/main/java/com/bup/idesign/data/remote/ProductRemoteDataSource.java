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

    private final RemoteServices remoteServices;

    public static ProductRemoteDataSource getInstance(RemoteServices remoteServices) {
        if (INSTANCE == null) {
            INSTANCE = new ProductRemoteDataSource(remoteServices);
        }
        return INSTANCE;
    }

    // Prevent direct instantiation.
    private ProductRemoteDataSource(RemoteServices remoteServices) {
        this.remoteServices = remoteServices;
    }

    @Override
    public void getProducts(final @NonNull LoadProductsCallback callback) {
        // Simulate network by delaying the execution.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                remoteServices.getAllProducts()
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

//                callback.onProductsLoaded(remoteServices.getAllProducts());
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
