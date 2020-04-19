package com.xstudioo.myfrigo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ProductListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Products> mProductList;

    //constructor
    public ProductListAdapter(Context mContext, List<Products> mProductList) {
        this.mContext = mContext;
        this.mProductList = mProductList;
    }

    @Override
    public int getCount() {
        return mProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return mProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.adapter_view_layout, null);

        TextView tvName = (TextView)v.findViewById(R.id.product_txt);
        TextView tvPurchase = (TextView)v.findViewById(R.id.purchase_txt);
        TextView tvExpiry = (TextView)v.findViewById(R.id.expiry_txt);

        //Set text for Textview
        tvName.setText((mProductList.get(position).getName()));
        tvPurchase.setText((mProductList.get(position).getPurchase_dte()));
        tvExpiry.setText((mProductList.get(position).getExpiry_dte()));

        //save product id to tag
        //v.setTag(mProductList.get(position).getId());

        return null;
    }
}
