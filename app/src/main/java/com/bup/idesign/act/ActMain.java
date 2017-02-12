package com.bup.idesign.act;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.bup.idesign.R;
import com.bup.idesign.adapter.MainProductAdapter;
import com.bup.idesign.model.ProductModel;
import com.bup.idesign.util.Util;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Albert-IM on 12/02/2017.
 */

public class ActMain extends AppCompatActivity {

    @BindView(R.id.rvMainProduct)
    RecyclerView rvMainProduct;

    MainProductAdapter mainProductAdapter;

    ArrayList<ProductModel> alProductModels = new ArrayList<>();

    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_main);

        ButterKnife.bind(this);
        Util.init(this);

        initProductData();
    }

    public void initProductData() {
        getDummyData();

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rvMainProduct.setLayoutManager(gridLayoutManager);

        mainProductAdapter = new MainProductAdapter(alProductModels);
        rvMainProduct.setAdapter(mainProductAdapter);
    }

    public void getDummyData() {
        alProductModels.clear();

        ProductModel model = new ProductModel();
        model.setProductSeq(0);
        model.setProductName("지윤이의 그림 티셔츠");
        model.setProductMainImage("http://blogfiles.naver.net/MjAxNzAyMDRfMTMz/MDAxNDg2MTM5MjM5NjE1.3POcPWupxnaHHwSkPRykE9_A_XLupeb425F2gZ592HEg.Z_OYLS221fiqpyhN-MOiRZKRS7I-pBJMXXdJM5tGer0g.JPEG.yasidaya/1.jpg");
        alProductModels.add(model);

        model = new ProductModel();
        model.setProductSeq(1);
        model.setProductName("이준이의 그림 액자");
        model.setProductMainImage("http://blogfiles.naver.net/MjAxNzAyMDVfODkg/MDAxNDg2MzA2MTEzMDEx.0xs7t-t9fU8tbX-TPPdkO_ZfiWVebIoliCMuqKuPtfMg.692gBosc3dE5GsP42-SZFcuc7u0p3a_jfT4WnloEqnQg.JPEG.yasidaya/2020-이준이_액자.jpg");
        alProductModels.add(model);

        model = new ProductModel();
        model.setProductSeq(2);
        model.setProductName("친구의 편지글귀 액자");
        model.setProductMainImage("http://blogfiles.naver.net/MjAxNzAxMjNfNzEg/MDAxNDg1MTc4OTg4MDkz.y5toNG5LRtdZNHy3en0chC81ZmbV47tm2SXCQDnqYlUg.8HOv8EfWK6wSb6Z1EPvRlsIXegBrR7BQqoHesMM6sjgg.JPEG.yasidaya/DSC00108.JPG");
        alProductModels.add(model);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, getString(R.string.app_exit), Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
