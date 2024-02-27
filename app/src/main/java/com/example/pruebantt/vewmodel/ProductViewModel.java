package com.example.pruebantt.vewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pruebantt.models.Product;

public class ProductViewModel extends ViewModel {

    private MutableLiveData<Product> product = new MutableLiveData<>();


    public LiveData<Product> getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product.setValue(product);
    }
}
