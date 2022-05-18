package com.ipy849.fastfoo.fragments.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ipy849.fastfoo.R;
import com.ipy849.fastfoo.adapters.RestaurantAdapter;
import com.ipy849.fastfoo.model.Restaurant;

import java.util.ArrayList;

public class MainFavoritoFragment extends Fragment {

    View rootView;
    RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_main_favorite, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = rootView.findViewById(R.id.fragment_main_home_recycler_favorite_restaurants);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(new RestaurantAdapter(GenerateRestaurants()));
    }

    public ArrayList<Restaurant> GenerateRestaurants() {
        ArrayList<Restaurant> restaurants = new ArrayList<>();

        Restaurant a = new Restaurant();
        a.setImage("https://www.elfinanciero.com.mx/resizer/20pV8H7W2orWcNWSlGDsiKnHREk=/400x225/filters:format(jpg):quality(70)/cloudfront-us-east-1.images.arcpublishing.com/elfinanciero/EMLSMCUMWFFAXO4ZS6VL3XYERE.jpg");
        a.setLiked(true);
        a.setTitle("Tacos en la calle");
        a.setUbication("Avenida yachxilan");

        Restaurant c = new Restaurant();
        c.setImage("https://github.com/bumptech/glide/raw/master/static/glide_logo.png");
        c.setLiked(true);
        c.setTitle("Tacos en la calle");
        c.setUbication("Avenida yachxilan");

        Restaurant d = new Restaurant();
        d.setImage("https://www.elfinanciero.com.mx/resizer/20pV8H7W2orWcNWSlGDsiKnHREk=/400x225/filters:format(jpg):quality(70)/cloudfront-us-east-1.images.arcpublishing.com/elfinanciero/EMLSMCUMWFFAXO4ZS6VL3XYERE.jpg");
        d.setLiked(true);
        d.setTitle("Tacos en la calle");
        d.setUbication("Avenida yachxilan");


        Restaurant f = new Restaurant();
        f.setImage("https://github.com/bumptech/glide/raw/master/static/glide_logo.png");
        f.setLiked(true);
        f.setTitle("Tacos en la calle");
        f.setUbication("Avenida yachxilan");

        restaurants.add(a);
        restaurants.add(c);
        restaurants.add(d);
        restaurants.add(f);
        return restaurants;
    }
}
