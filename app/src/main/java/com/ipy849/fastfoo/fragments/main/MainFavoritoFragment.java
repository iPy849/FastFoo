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

import com.ipy849.fastfoo.AppSession;
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
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView.setAdapter(new RestaurantAdapter(AppSession.user.getFavoriteRestaurant()));
    }
}
