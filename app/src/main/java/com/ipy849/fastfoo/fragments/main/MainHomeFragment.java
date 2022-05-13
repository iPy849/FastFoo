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
import com.ipy849.fastfoo.fragments.main.adapters.RestaurantAdapter;
import com.ipy849.fastfoo.model.Restaurant;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

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
        recyclerView.setAdapter(new RestaurantAdapter(GenerateRestaurants()));
    }

    public ArrayList<Restaurant> GenerateRestaurants() {
        ArrayList<Restaurant> restaurants = new ArrayList<>();

        URI ra = null;
        try {
            ra = new URI("https://www.elfinanciero.com.mx/resizer/20pV8H7W2orWcNWSlGDsiKnHREk=/400x225/filters:format(jpg):quality(70)/cloudfront-us-east-1.images.arcpublishing.com/elfinanciero/EMLSMCUMWFFAXO4ZS6VL3XYERE.jpg");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Restaurant a = new Restaurant(true, "Avenida yachxilan", "Tacos en la calle", ra);

        URI rb = null;
        try {
            rb = new URI("https://image.shutterstock.com/image-photo/mcdonalds-logo-has-branches-around-260nw-1629323827.jpg");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Restaurant b = new Restaurant(false, "Nichupté", "Macdonalds", rb);

        restaurants.add(a);
        restaurants.add(b);
        return restaurants;
    }
}
