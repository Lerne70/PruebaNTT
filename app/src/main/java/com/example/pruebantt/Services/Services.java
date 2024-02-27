package com.example.pruebantt.Services;

import com.example.pruebantt.models.GeneralProdcut;
import com.example.pruebantt.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Services {

    @GET("products")
    Call<GeneralProdcut> listProduct();

}
