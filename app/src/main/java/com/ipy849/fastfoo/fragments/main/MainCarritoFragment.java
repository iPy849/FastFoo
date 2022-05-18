package com.ipy849.fastfoo.fragments.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ipy849.fastfoo.BuildConfig;
import com.ipy849.fastfoo.R;
import com.ipy849.fastfoo.adapters.CarritoAdapter;
import com.ipy849.fastfoo.model.Product;

public class MainCarritoFragment extends Fragment {

    View rootView;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_main_carrito, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = rootView.findViewById(R.id.fragment_main_map_recycler_map_legend);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new CarritoAdapter(GenerateProducts()));
    }

    public Product[] GenerateProducts() {
        Product[] products = new Product[3];
        products[0] = new Product();
        products[0].setPrice(200);
        products[0].setName(BuildConfig.MAPS_API_KEY);
        products[0].setDescription("Pizza de queso extra");

        products[1] = new Product();
        products[1].setPrice(250);
        products[1].setName("Pizza media");
        products[1].setDescription("Pizza con peperoni");

        products[2] = new Product();
        products[2].setPrice(300);
        products[2].setName("Pizza rica");
        products[2].setDescription("Pizza hawaiana");
        return products;
    }
}
