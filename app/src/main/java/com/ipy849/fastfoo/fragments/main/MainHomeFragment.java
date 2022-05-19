package com.ipy849.fastfoo.fragments.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ipy849.fastfoo.AppSession;
import com.ipy849.fastfoo.R;
import com.ipy849.fastfoo.adapters.RestaurantAdapter;
import com.ipy849.fastfoo.model.Product;
import com.ipy849.fastfoo.model.Restaurant;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainHomeFragment extends Fragment {

    View rootView;
    RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_main_home, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = rootView.findViewById(R.id.fragment_main_home_recycler_restaurants);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Traer restaurantes de la base de datos
        DatabaseReference fbDatabaseRef = FirebaseDatabase.getInstance().getReference();
        fbDatabaseRef.child("restaurants").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                ArrayList<HashMap> restaurantsData = ((ArrayList) task.getResult().getValue());
                ArrayList<Restaurant> restaurants = new ArrayList<>();
                for (HashMap<String, Object> restaurantMap: restaurantsData) {
                    Restaurant restaurant = new Restaurant(restaurantMap);
                    restaurants.add(restaurant);
                }

                recyclerView.setAdapter(new RestaurantAdapter(restaurants));
            }
        });
    }
}
