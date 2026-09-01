package com.example.classwork_recyclerview.model;

import retrofit2.Call;
import retrofit2.http.GET;

import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IGetAllProduct {
    @GET("products")
    Call<ProductResponse> getAllProducts(
            @Query("limit") int limit,
            @Query("skip") int skip
    );

    @GET("products/{id}")
    Call<Product> getProductById(@Path("id") int id);
}
