package com.ipy849.fastfoo;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ipy849.fastfoo.adapters.CarritoAdapter;
import com.ipy849.fastfoo.adapters.ShopAdapter;
import com.ipy849.fastfoo.dialogHandlers.DialogFragmentTextOk;
import com.ipy849.fastfoo.model.Restaurant;

import java.util.HashMap;


public class ShopDetail extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
    }

    @Override
    protected void onResume() {
        super.onResume();
        DatabaseReference product = FirebaseDatabase.getInstance().getReference();
        product.child("restaurants").child(String.valueOf(AppSession.shopId)).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(! task.isSuccessful()) {
                    new DialogFragmentTextOk("Lo siento estamos teniendo problemas", getText(R.string.ok).toString()).show(getSupportFragmentManager(), DialogFragmentTextOk.TAG);
                    finish();
                }

                Restaurant restaurant = new Restaurant(((HashMap<String, Object>)task.getResult().getValue()));
                ((TextView) findViewById(R.id.shop_name)).setText(restaurant.getName());
                Glide.with(getApplicationContext()).load(restaurant.getImage()).into((ImageView) findViewById(R.id.shop_image));
                recyclerView = findViewById(R.id.shop_products);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(new ShopAdapter(restaurant.getProductos()));
            }
        });
    }
}