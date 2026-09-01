package com.example.classwork_recyclerview.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.classwork_recyclerview.adapter.DetailImageAdapter;
import com.example.classwork_recyclerview.databinding.ActivityProductDetailBinding;
import com.example.classwork_recyclerview.model.IGetAllProduct;
import com.example.classwork_recyclerview.model.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductDetailActivity extends AppCompatActivity {

    private ActivityProductDetailBinding binding;
    private IGetAllProduct api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dummyjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(IGetAllProduct.class);

        int productId = getIntent().getIntExtra("PRODUCT_ID", -1);
        if (productId != -1) {
            fetchProductDetails(productId);
        } else {
            Toast.makeText(this, "Product ID not found", Toast.LENGTH_SHORT).show();
            finish();
        }

        binding.btnBack.setOnClickListener(v -> finish());
    }

    private void fetchProductDetails(int id) {
        api.getProductById(id).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(@NonNull Call<Product> call, @NonNull Response<Product> response) {
                if (response.isSuccessful() && response.body() != null) {
                    updateUI(response.body());
                } else {
                    Log.e("ProductDetailActivity", "Response failed");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Product> call, @NonNull Throwable t) {
                Log.e("ProductDetailActivity", "Error: " + t.getMessage());
            }
        });
    }

    private void updateUI(Product product) {
        binding.tvDetailTitle.setText(product.getTitle());
        binding.tvDetailPrice1.setText("$ " + product.getPrice());
        double discountedPrice = product.getPrice() * (1 - product.getDiscountPercentage() / 100);
        binding.tvDetailPrice2.setText("$ " + String.format("%.2f", discountedPrice));
        binding.tvDetailDesc.setText(product.getDescription());
        
        setupRecyclerView(product.getImages());
    }

    private void setupRecyclerView(List<String> images) {
        DetailImageAdapter adapter = new DetailImageAdapter(images);
        binding.rvProductDetail.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.rvProductDetail.setAdapter(adapter);
    }
}
