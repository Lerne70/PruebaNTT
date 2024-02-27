package com.example.pruebantt.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pruebantt.R;
import com.example.pruebantt.models.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> mData;
    private LayoutInflater mInflate;
    private Context context;

    private OnClickListener onClickListener;

    public ProductAdapter(List<Product> itemList, Context context) {
        this.mInflate = LayoutInflater.from(context);
        this.mData = itemList;
        this.context = context;
    }

    @Override
    public int getItemCount() {return mData.size();}

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.product, null);
        return new ProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Product product = mData.get(position);
        holder.bindData(mData.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(onClickListener != null) {
                    onClickListener.onClick(position, product);
                }
            }
        });
    }


    public void setOnclickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int position, Product product);
    }

    public void setItem(List<Product> items) {this.mData = items;}

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewProductName, textViewProductDescription, textViewProductPrice;
        ImageView imgProduct;
        public ViewHolder(View itemView) {
            super(itemView);
            textViewProductName = itemView.findViewById(R.id.text_view_product_name);
            textViewProductDescription = itemView.findViewById(R.id.text_view_product_description);
            textViewProductPrice = itemView.findViewById(R.id.text_view_product_price);
            imgProduct = itemView.findViewById(R.id.img_product);
        }

        public void bindData(final Product item) {
            Glide
             .with(itemView)
             .load(item.getThumbnail())
             .centerCrop()
             .into(imgProduct);
            textViewProductName.setText(item.getTitle());
            textViewProductDescription.setText(item.getDescription());
            textViewProductPrice.setText(String.valueOf(item.getPrice()));
        }
    }
}
