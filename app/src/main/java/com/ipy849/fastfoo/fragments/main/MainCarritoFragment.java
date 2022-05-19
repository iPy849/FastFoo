package com.ipy849.fastfoo.fragments.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ipy849.fastfoo.AppSession;
import com.ipy849.fastfoo.BuildConfig;
import com.ipy849.fastfoo.MainActivity;
import com.ipy849.fastfoo.R;
import com.ipy849.fastfoo.adapters.CarritoAdapter;
import com.ipy849.fastfoo.dialogHandlers.DialogFragmentConfirmationText;
import com.ipy849.fastfoo.model.Product;

public class MainCarritoFragment extends Fragment {

    View rootView;
    RecyclerView recyclerView;
    MainActivity caller;


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
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView.setAdapter(new CarritoAdapter(AppSession.user.getCart()));

        (rootView.findViewById(R.id.fragment_main_home_recycler_carrito_fab_title)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float total = 0;
                for (Product product: AppSession.user.getCart()) {
                    total += product.getPrice();
                }
                new DialogFragmentConfirmationText(String.format("Se va a pagar el monto de %.2f pesos. Desea pagarlos?", total), "Pagar", caller).show(getParentFragmentManager(), DialogFragmentConfirmationText.TAG);
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context != null) {
            caller = (MainActivity) context;
        }
    }
}
