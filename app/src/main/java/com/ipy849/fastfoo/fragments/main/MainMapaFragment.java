package com.ipy849.fastfoo.fragments.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ipy849.fastfoo.BuildConfig;
import com.ipy849.fastfoo.MainActivity;
import com.ipy849.fastfoo.R;
import com.ipy849.fastfoo.adapters.CarritoAdapter;
import com.ipy849.fastfoo.model.Product;

public class MainMapaFragment extends Fragment implements OnMapReadyCallback {

    View rootView;
    MainActivity activity;
    private GoogleMap map;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_main_mapa, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragment_main_map_map);
        if (mapFragment != null)
            mapFragment.getMapAsync(this);


        recyclerView = rootView.findViewById(R.id.fragment_main_map_recycler_map_legend);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        //recyclerView.setAdapter(new CarritoAdapter(GenerateProducts()));

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        LatLng cancun = new LatLng(21.1619, -86.8515);
        map.addMarker(new MarkerOptions()
                .position(cancun)
                .title("Aquí está Cancún"));
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        map.moveCamera(CameraUpdateFactory.zoomBy(5));
        map.moveCamera(CameraUpdateFactory.newLatLng(cancun));
        map.setTrafficEnabled(true);
    }

    // Al enlazar el fragment
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof MainActivity){
            activity = (MainActivity) context;
        }
    }
}
