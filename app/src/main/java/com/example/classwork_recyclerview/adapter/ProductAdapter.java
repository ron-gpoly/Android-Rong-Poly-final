package com.example.classwork_recyclerview.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.classwork_recyclerview.databinding.ItemLayoutBinding;
import com.example.classwork_recyclerview.model.Product;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<Product> products;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Product product);
    }

    public ProductAdapter(List<Product> products, OnItemClickListener listener) {
        this.products = products;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);
        
        Glide.with(holder.itemView.getContext())
                .load(product.getThumbnail())
                .into(holder.binding.image);
                
        holder.binding.title.setText(product.getTitle());
        holder.binding.desc.setText(product.getDescription());
        holder.binding.price1.setText("$ " + product.getPrice());
        // price2 can be used for discounted price if needed
        double discountedPrice = product.getPrice() * (1 - product.getDiscountPercentage() / 100);
        holder.binding.price2.setText("$ " + String.format("%.2f", discountedPrice));
        
        holder.binding.onClick.setOnClickListener(v -> listener.onItemClick(product));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void addProducts(List<Product> newProducts) {
        int startPosition = products.size();
        products.addAll(newProducts);
        notifyItemRangeInserted(startPosition, newProducts.size());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ItemLayoutBinding binding;
        public ViewHolder(ItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
