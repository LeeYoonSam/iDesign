package com.bup.idesign.products;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bup.idesign.R;
import com.bup.idesign.data.RemoteService;
import com.bup.idesign.util.ActivityUtils;
import com.bup.idesign.util.Util;

import butterknife.ButterKnife;

/**
 * Created by Albert-IM on 12/02/2017.
 */

public class ProductAct extends AppCompatActivity {

    private ProductsPresenter mProductsPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_main_frame);

        ButterKnife.bind(this);
        Util.init(this);

        ProductFrag productFrag =
                (ProductFrag) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (productFrag == null) {
            // Create the fragment
            productFrag = ProductFrag.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), productFrag, R.id.contentFrame);
        }

        // Create the presenter
        mProductsPresenter = new ProductsPresenter(RemoteService.getInstance(), productFrag);

    }

}
