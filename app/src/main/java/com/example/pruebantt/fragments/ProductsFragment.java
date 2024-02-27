package com.example.pruebantt.fragments;

import static com.example.pruebantt.Services.API.retrofit;

import android.os.Bundle;

import androidx.annotation.NavigationRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pruebantt.R;
import com.example.pruebantt.Services.Services;
import com.example.pruebantt.adapters.ProductAdapter;
import com.example.pruebantt.databinding.FragmentProductsBinding;
import com.example.pruebantt.models.GeneralProdcut;
import com.example.pruebantt.models.Product;
import com.example.pruebantt.vewmodel.ProductViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsFragment extends Fragment {

    private FragmentProductsBinding binding;
    private ProductAdapter productAdapter;
    private Services services;

    private ProductViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProductsBinding.inflate(inflater, container, false);

        services = retrofit.create(Services.class);

        viewModel = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getProdutsList();
    }

    private void getProdutsList() {
        Call<GeneralProdcut> productList = services.listProduct();

        productList.enqueue(new Callback<GeneralProdcut>() {
            @Override
            public void onResponse(Call<GeneralProdcut> call, Response<GeneralProdcut> response) {
                productAdapter = new ProductAdapter(response.body().getProducts(), requireContext());
                binding.recyclerViewProducts.setHasFixedSize(true);
                binding.recyclerViewProducts.setLayoutManager(new LinearLayoutManager(requireContext()));
                binding.recyclerViewProducts.setAdapter(productAdapter);

                productAdapter.setOnclickListener(new ProductAdapter.OnClickListener() {
                    @Override
                    public void onClick(int position, Product product) {
                        viewModel.setProduct(product);
                        Navigation.findNavController(requireView()).navigate(R.id.action_productsFragment_to_detailProductFragment);
                    }
                });
            }

            @Override
            public void onFailure(Call<GeneralProdcut> call, Throwable t) {
                Log.e("TAG", "onFailure: ", t);
            }
        });
    }
}