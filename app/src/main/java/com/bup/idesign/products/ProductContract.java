package com.bup.idesign.products;

import com.bup.idesign.BasePresenter;
import com.bup.idesign.BaseView;
import com.bup.idesign.model.Product;

import java.util.List;

/**
 * Created by Albert-IM on 13/02/2017.
 */

public class ProductContract {

    public interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showProducts(List<Product> products);

        void showLoadingProductsError();

        void showNoProducts();

        boolean isActive();
    }

    public interface Presenter extends BasePresenter {
        void loadProducts(boolean forceUpdate);
    }
}
