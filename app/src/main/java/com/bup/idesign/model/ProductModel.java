package com.bup.idesign.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Albert-IM on 12/02/2017.
 */

public class ProductModel implements Parcelable {

    public int cateSeq;
    public String cateName;

    public int productSeq;
    public String productName;
    public String productMainImage;
    public String productDescription;
    public String productDate;

    public ArrayList<String> productImageList;

    public ProductModel() {
        cateSeq = 0;
        cateName = null;

        productSeq = 0;
        productName = null;
        productMainImage = null;
        productDescription = null;
        productDate = null;

        productImageList = new ArrayList<>();
    }

    public int getCateSeq() {
        return cateSeq;
    }

    public void setCateSeq(int cateSeq) {
        this.cateSeq = cateSeq;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public int getProductSeq() {
        return productSeq;
    }

    public void setProductSeq(int productSeq) {
        this.productSeq = productSeq;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductMainImage() {
        return productMainImage;
    }

    public void setProductMainImage(String productMainImage) {
        this.productMainImage = productMainImage;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductDate() {
        return productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    public ArrayList<String> getProductImageList() {
        return productImageList;
    }

    public void setProductImageList(ArrayList<String> productImageList) {
        this.productImageList = productImageList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(cateSeq);
        dest.writeString(cateName);
        dest.writeInt(productSeq);
        dest.writeString(productName);
        dest.writeString(productDescription);
        dest.writeString(productDate);
        dest.writeStringList(productImageList);
    }

    protected ProductModel(Parcel in) {

        cateSeq = in.readInt();
        cateName = in.readString();
        productSeq = in.readInt();
        productName = in.readString();
        productDescription = in.readString();
        productDate = in.readString();

        productImageList = new ArrayList<>();
        in.readStringList(productImageList);
    }

    public static final Creator<ProductModel> CREATOR = new Creator<ProductModel>() {
        @Override
        public ProductModel createFromParcel(Parcel in) {
            return new ProductModel(in);
        }

        @Override
        public ProductModel[] newArray(int size) {
            return new ProductModel[size];
        }
    };


}
