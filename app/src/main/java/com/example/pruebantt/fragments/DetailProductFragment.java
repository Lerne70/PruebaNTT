package com.example.pruebantt.fragments;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.pruebantt.R;
import com.example.pruebantt.databinding.FragmentDetailProductBinding;
import com.example.pruebantt.models.Product;
import com.example.pruebantt.vewmodel.ProductViewModel;

public class DetailProductFragment extends Fragment {

    private FragmentDetailProductBinding binding;
    private ProductViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailProductBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Product product = viewModel.getProduct().getValue();

        String price = getString(R.string.price) + " " + String.valueOf(product.getPrice());

        binding.textViewTitleProduct.setText(product.getTitle());
        Glide
                .with(requireContext())
                .load(product.getThumbnail())
                .centerCrop()
                .into(binding.imageViewProduct);
        binding.textViewProductDescriptionDetail.setText(product.getDescription());
        binding.textViewProductPriceDetail.setText(price);

        binding.btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });

    }
}