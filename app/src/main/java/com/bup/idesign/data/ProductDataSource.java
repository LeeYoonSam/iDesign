package com.bup.idesign.data;

import android.database.Observable;
import android.support.annotation.NonNull;

import com.bup.idesign.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Albert-IM on 13/02/2017.
 */

public interface ProductDataSource {
    interface LoadProductsCallback {

        void onProductsLoaded(List<Product> products);
        void onProductsLoaded(rx.Observable<ArrayList<Product>> products);

        void onDataNotAvailable();
    }

    interface GetProductCallback {

        void onProductLoaded(Product product);
        void onProductsLoaded(Observable<Product> product);

        void onDataNotAvailable();
    }

    void getProducts(@NonNull LoadProductsCallback callback);

    void getProduct(@NonNull String productSeq, @NonNull GetProductCallback callback);

    void refreshProducts();
}
