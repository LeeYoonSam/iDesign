package com.bup.idesign.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bup.idesign.R;
import com.bup.idesign.model.ProductModel;
import com.bup.idesign.util.Util;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Albert-IM on 12/02/2017.
 */

public class MainProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int HEADER_VIEW = 1;
    private static final int FOOTER_VIEW = 2;

    private ArrayList<ProductModel> alProduct;

    public MainProductAdapter(ArrayList<ProductModel> data) {
        alProduct = new ArrayList<>();
        alProduct.addAll(data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_main_product, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        try {
            ViewHolder viewHolder = (ViewHolder)holder;

            ProductModel item = alProduct.get(position);
            viewHolder.tvProductName.setText(item.getProductName());

            if(item.getProductMainImage() != null) {
                Util.setGlideLoadWithSizeWithoutPlaceholder(viewHolder.itemView.getContext(), item.getProductMainImage(), Util.getDensityHeight(180), Util.getDensityHeight(180), viewHolder.ivProductMainImage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return alProduct.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tvProductName)
        TextView tvProductName;

        @BindView(R.id.ivProductMainImage)
        ImageView ivProductMainImage;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            Intent intent = new Intent(getActivity(), ActivityDetailBoard.class);
//            intent.putExtra("data", data.get(rv.getChildLayoutPosition(v)));
//            startActivity(intent);
            Toast.makeText(v.getContext(), "connect detail page", Toast.LENGTH_SHORT).show();
        }
    }
}
