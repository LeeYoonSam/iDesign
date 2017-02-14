package com.bup.idesign.products;

import android.support.annotation.NonNull;

import com.bup.idesign.data.ProductDataSource;
import com.bup.idesign.data.remote.ProductRemoteDataSource;
import com.bup.idesign.data.remote.RemoteApi;
import com.bup.idesign.model.Product;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscription;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Albert-IM on 13/02/2017.
 */

public class ProductsPresenter implements ProductContract.Presenter {

    private final ProductContract.View mProductsView;
    private ProductRemoteDataSource mProductRemoteDataSource;

    private final RemoteApi mRemoteApi;

    private Subscription mSubscription;

    private boolean mFirstLoad = true;

//    public ProductsPresenter(@NonNull ProductContract.View productsView) {
//        mProductsView = checkNotNull(productsView, "productsView cannot be null!");
//        mProductsView.setPresenter(this);
//    }

    public ProductsPresenter(@NonNull RemoteApi remoteApi, @NonNull ProductContract.View productsView) {

        mRemoteApi = checkNotNull(remoteApi, "RemoteApi cannot be null");
        mProductsView = checkNotNull(productsView, "productsView cannot be null!");
        mProductsView.setPresenter(this);

        mProductRemoteDataSource = ProductRemoteDataSource.getInstance(remoteApi);
    }



//    public ProductsPresenter(@NonNull ProductRemoteDataSource productRemoteDataSource, @NonNull ProductContract.View productsView) {
//
//        productRemoteDataSource = checkNotNull(productRemoteDataSource, "ProductRemoteDataSource cannot be null");
//        mProductsView = checkNotNull(productsView, "productsView cannot be null!");
//        mProductsView.setPresenter(this);
//    }

    @Override
    public void start() {
        loadProducts(false);
    }

    @Override
    public void loadProducts(boolean forceUpdate) {
        // Simplification for sample: a network reload will be forced on first load.
        loadProducts(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    private void processProducts(List<Product> products) {
        if (products.isEmpty()) {
            // Show a message indicating there are no tasks for that filter type.
            mProductsView.showNoProducts();
        } else {
            // Show the list of tasks
            mProductsView.showProducts(products);
        }
    }

    private void loadProducts(boolean forceUpdate, final boolean showLoadingUI) {
        if (showLoadingUI) {
            mProductsView.setLoadingIndicator(true);
        }
        if (forceUpdate) {
            mProductRemoteDataSource.refreshProducts();
        }

        mProductRemoteDataSource.getProducts(new ProductDataSource.LoadProductsCallback() {

            @Override
            public void onProductsLoaded(List<Product> products) {

                // The view may not be able to handle UI updates anymore
                if (!mProductsView.isActive()) {
                    return;
                }
                if (showLoadingUI) {
                    mProductsView.setLoadingIndicator(false);
                }

                processProducts(products);
            }

            @Override
            public void onProductsLoaded(Observable<ArrayList<Product>> products) {
            }

            @Override
            public void onDataNotAvailable() {
                // The view may not be able to handle UI updates anymore
                if (!mProductsView.isActive()) {
                    return;
                }
                mProductsView.showLoadingProductsError();
            }
        });
    }
}
