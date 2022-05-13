package com.ipy849.fastfoo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.ipy849.fastfoo.fragments.main.MainCarritoFragment;
import com.ipy849.fastfoo.fragments.main.MainFavoritoFragment;
import com.ipy849.fastfoo.fragments.main.MainHomeFragment;
import com.ipy849.fastfoo.fragments.main.MainMapaFragment;
import com.ipy849.fastfoo.fragments.main.MainProfileFragment;


public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    BottomNavigationView navigationView;
    FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Inciar fragment home
        frameLayout = findViewById(R.id.main_frameLayout);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setReorderingAllowed(true);
        ft.replace(frameLayout.getId(), MainHomeFragment.class, null);
        ft.commit();


        // Cambiar la validación a firebase
        if (false) {
            Intent loginIntent = new Intent(this, Login.class);
            startActivity(loginIntent);
        }

        // Evento de selección del menú
        navigationView = findViewById(R.id.main_bottom_navigation);
        navigationView.setOnItemSelectedListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Class fragmentClass;
        switch(item.getItemId()){
            case R.id.main_navigation_home:
                fragmentClass = MainHomeFragment.class;
                break;
            case R.id.main_navigation_favoritos:
                fragmentClass = MainFavoritoFragment.class;
                break;
            case R.id.main_navigation_carrito:
                fragmentClass = MainCarritoFragment.class;
                break;
            case R.id.main_navigation_maps:
                fragmentClass = MainMapaFragment.class;
                break;
            case R.id.main_navigation_profile:
                fragmentClass = MainProfileFragment.class;
                break;
            default:
                return false;
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setReorderingAllowed(true);
        ft.replace(frameLayout.getId(), fragmentClass, null);
        ft.commit();

        return true;
    }
}