package com.bup.idesign.products;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bup.idesign.R;
import com.bup.idesign.adapter.MainProductAdapter;
import com.bup.idesign.model.Product;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Albert-IM on 14/02/2017.
 */

public class ProductFrag extends Fragment implements ProductContract.View {
    @BindView(R.id.rvMainProduct)
    RecyclerView rvMainProduct;

    @BindView(R.id.refresh_layout)
    ScrollChildSwipeRefreshLayout swipeRefreshLayout;

    MainProductAdapter mainProductAdapter;

    ArrayList<Product> alProducts = new ArrayList<>();

    public static ProductFrag newInstance() {
        return new ProductFrag();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_main, null);

        ButterKnife.bind(this, view);

        // Set up progress indicator
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );

        // Set the scrolling view in the custom SwipeRefreshLayout.
        swipeRefreshLayout.setScrollUpChild(rvMainProduct);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                mPresenter.loadTasks(false);

                Toast.makeText(getActivity(), "Refresh", Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initProductData();
    }

    public void initProductData() {
        getDummyData();

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        rvMainProduct.setLayoutManager(gridLayoutManager);

        mainProductAdapter = new MainProductAdapter(alProducts);
        rvMainProduct.setAdapter(mainProductAdapter);
    }

    public void getDummyData() {
        alProducts.clear();

        Product model = new Product();
        model.setProductSeq(0);
        model.setProductName("지윤이의 그림 티셔츠");
        model.setProductMainImage("http://blogfiles.naver.net/MjAxNzAyMDRfMTMz/MDAxNDg2MTM5MjM5NjE1.3POcPWupxnaHHwSkPRykE9_A_XLupeb425F2gZ592HEg.Z_OYLS221fiqpyhN-MOiRZKRS7I-pBJMXXdJM5tGer0g.JPEG.yasidaya/1.jpg");
        alProducts.add(model);

        model = new Product();
        model.setProductSeq(1);
        model.setProductName("이준이의 그림 액자");
        model.setProductMainImage("http://blogfiles.naver.net/MjAxNzAyMDVfODkg/MDAxNDg2MzA2MTEzMDEx.0xs7t-t9fU8tbX-TPPdkO_ZfiWVebIoliCMuqKuPtfMg.692gBosc3dE5GsP42-SZFcuc7u0p3a_jfT4WnloEqnQg.JPEG.yasidaya/2020-이준이_액자.jpg");
        alProducts.add(model);

        model = new Product();
        model.setProductSeq(2);
        model.setProductName("친구의 편지글귀 액자");
        model.setProductMainImage("http://blogfiles.naver.net/MjAxNzAxMjNfNzEg/MDAxNDg1MTc4OTg4MDkz.y5toNG5LRtdZNHy3en0chC81ZmbV47tm2SXCQDnqYlUg.8HOv8EfWK6wSb6Z1EPvRlsIXegBrR7BQqoHesMM6sjgg.JPEG.yasidaya/DSC00108.JPG");
        alProducts.add(model);
    }

    @Override
    public void setLoadingIndicator(final boolean active) {

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(active);
            }
        });
    }

    @Override
    public void showProducts(List<Product> products) {

    }

    @Override
    public void showLoadingProductsError() {

    }

    @Override
    public void showNoProducts() {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void setPresenter(ProductContract.Presenter presenter) {

    }
}
