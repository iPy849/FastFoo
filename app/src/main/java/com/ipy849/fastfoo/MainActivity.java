package com.ipy849.fastfoo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.ipy849.fastfoo.fragments.main.MainHomeFragment;


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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragmentClass = null;
        switch(item.getItemId()){
            case R.id.main_navigation_home:
                break;
            case R.id.main_navigation_favoritos:
                break;
            case R.id.main_navigation_carrito:
                break;
            case R.id.main_navigation_maps:
                break;
            case R.id.main_navigation_profile:
                break;
            default:
                break;
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setReorderingAllowed(true);
        ft.replace(frameLayout.getId(), fragmentClass, null);
        ft.commit();

        return true;
    }
}