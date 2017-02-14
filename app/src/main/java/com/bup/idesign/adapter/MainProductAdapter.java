package com.bup.idesign.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bup.idesign.R;
import com.bup.idesign.model.Product;
import com.bup.idesign.util.Util;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Albert-IM on 12/02/2017.
 */

public class MainProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_HEADER = 0;
    public static final int VIEW_TYPE_ITEM = 1;

    private ArrayList<Product> alProduct;

    public MainProductAdapter(ArrayList<Product> data) {
        alProduct = new ArrayList<>();
        alProduct.addAll(data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_HEADER) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cell_main_product_section, null);

            return new HeaderViewHolder(itemView);
        } else {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cell_main_product, parent, false);

            return new ViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        try {
            if (holder instanceof HeaderViewHolder) {
                HeaderViewHolder headerViewHolder = (HeaderViewHolder)holder;

                final Product item = alProduct.get(position);
                headerViewHolder.tvProductSection.setText(item.cateName);
            }
            else {
                ViewHolder viewHolder = (ViewHolder)holder;

                final Product item = alProduct.get(position);
                viewHolder.tvProductName.setText(item.getProductName());

                if(item.getProductMainImage() != null) {
                    Util.setGlideLoadWithSizeWithoutPlaceholder(viewHolder.itemView.getContext(), item.getProductMainImage(), Util.getDensityHeight(180), Util.getDensityHeight(180), viewHolder.ivProductMainImage);
                }
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
//        if(alProduct.get(position).cateSeq == 1) {
//            return VIEW_TYPE_HEADER;
//        } else {
//            return VIEW_TYPE_ITEM;
//        }

        return VIEW_TYPE_ITEM;
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

    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvProductSection)
        TextView tvProductSection;

        public HeaderViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
