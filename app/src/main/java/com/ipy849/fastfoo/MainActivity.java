package com.ipy849.fastfoo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ipy849.fastfoo.fragments.main.MainCarritoFragment;
import com.ipy849.fastfoo.fragments.main.MainFavoritoFragment;
import com.ipy849.fastfoo.fragments.main.MainHomeFragment;
import com.ipy849.fastfoo.fragments.main.MainMapaFragment;
import com.ipy849.fastfoo.fragments.main.MainProfileFragment;
import com.ipy849.fastfoo.model.User;
import com.ipy849.fastfoo.utils.SharedPreferencesFiles;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    BottomNavigationView navigationView;
    FrameLayout frameLayout;

    FirebaseAuth fbAuth;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        sharedPreferences = getSharedPreferences(SharedPreferencesFiles.USERS_FILE.name(), MODE_PRIVATE);

        // Validación de usuario
        fbAuth = FirebaseAuth.getInstance();
        if (fbAuth.getCurrentUser() == null) {
            Intent loginIntent = new Intent(this, Login.class);
            startActivity(loginIntent);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        // El usuario no está cargado
        if (AppSession.user == null) {
            DatabaseReference fbDatabaseRef = FirebaseDatabase.getInstance().getReference();
            fbDatabaseRef.child("users").child(sharedPreferences.getString("uid", "")).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) return;
                    HashMap<String, Object> data = (HashMap<String, Object>) task.getResult().getValue();
                    new User(data);

                    // Inciar fragment home
                    frameLayout = findViewById(R.id.main_frameLayout);
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.setReorderingAllowed(true);
                    ft.replace(frameLayout.getId(), MainHomeFragment.class, null);
                    ft.commit();
                }
            });
        } else if (AppSession.user != null){
            // Inciar fragment home
            frameLayout = findViewById(R.id.main_frameLayout);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setReorderingAllowed(true);
            ft.replace(frameLayout.getId(), MainHomeFragment.class, null);
            ft.commit();
        }



        // Evento de selección del menú
        navigationView = findViewById(R.id.main_bottom_navigation);
        navigationView.setOnItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Class fragmentClass;
        switch (item.getItemId()) {
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