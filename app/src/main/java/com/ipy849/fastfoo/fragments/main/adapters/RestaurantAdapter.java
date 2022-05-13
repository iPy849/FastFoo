package com.ipy849.fastfoo.fragments.main.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ipy849.fastfoo.R;
import com.ipy849.fastfoo.model.Restaurant;


import java.net.URI;
import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter {
    private ArrayList<Restaurant> restaurantsData;

    public RestaurantAdapter(ArrayList<Restaurant> data) {
        restaurantsData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_restaurant_grid, parent, false);
        return new RestaurantViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RestaurantAdapter.RestaurantViewholder _holder = (RestaurantAdapter.RestaurantViewholder) holder;
        Restaurant restaurant = restaurantsData.get(position);
        _holder.setRestaurant(restaurant);
        _holder.setImage(restaurant.getImage());
        _holder.setLikeButton(restaurant.isLiked());
        _holder.setTitle(restaurant.getTitle());
        _holder.setLocation(restaurant.getUbication());
    }

    @Override
    public int getItemCount() {
        return restaurantsData.size();
    }

    public static class RestaurantViewholder extends RecyclerView.ViewHolder{

        public void setImage(@NonNull URI uri) {
            Log.d("ALV", "setImage: " + uri.toString());
            Glide.with(this.view).load(uri).into(this.image);
        }

        public void setLikeButton(@NonNull Boolean likeButton) {
            this.likeButton.setImageResource(likeButton ? R.drawable.ic_baseline_favorite_24_active : R.drawable.ic_baseline_favorite_24);
        }

        public void setTitle(String title) {
            this.title.setText(title);
        }

        public void setLocation(String location) {
            this.location.setText(location);
        }

        public void setRestaurant(Restaurant restaurant){
            this.restaurant = restaurant;
        }

        ImageView image;
        ImageButton likeButton;
        TextView title;
        TextView location;
        Restaurant restaurant;
        private final View view;

        public RestaurantViewholder(@NonNull View view) {
            super(view);
            this.view = view;
            image = view.findViewById(R.id.recyclerview_restaurant_grid_image);
            likeButton = view.findViewById(R.id.recyclerview_restaurant_grid_like);
            title = view.findViewById(R.id.recyclerview_restaurant_grid_title);
            location = view.findViewById(R.id.recyclerview_restaurant_grid_ubication);

            likeButton.setOnClickListener(view1 -> likeButtonEvent());
        }

        private void likeButtonEvent(){
            // TODO: Implementar el evento de click en el botón de la tarjeta
        }
    }
}
