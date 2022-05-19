package com.ipy849.fastfoo.adapters;

import android.content.Intent;
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
import com.ipy849.fastfoo.AppSession;
import com.ipy849.fastfoo.R;
import com.ipy849.fastfoo.Register;
import com.ipy849.fastfoo.ShopDetail;
import com.ipy849.fastfoo.model.Restaurant;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewholder> {
    private ArrayList<Restaurant> restaurantsData;

    public RestaurantAdapter(ArrayList<Restaurant> data) {
        restaurantsData = data;
    }

    @NonNull
    @Override
    public RestaurantAdapter.RestaurantViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_restaurant_grid, parent, false);
        return new RestaurantViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantAdapter.RestaurantViewholder holder, int position) {
        holder.updateRestaurantView(this.restaurantsData.get(position), position);
    }

    @Override
    public int getItemCount() {
        return this.restaurantsData.size();
    }

    public static class RestaurantViewholder extends RecyclerView.ViewHolder{

        ImageView image;
        ImageButton likeButton;
        TextView title;
        TextView location;
        Restaurant restaurant;
        int position;
        View view;

        public RestaurantViewholder(@NonNull View view) {
            super(view);
            this.view = view;
            image = view.findViewById(R.id.recyclerview_restaurant_grid_image);
            likeButton = view.findViewById(R.id.recyclerview_restaurant_grid_like);
            title = view.findViewById(R.id.recyclerview_restaurant_grid_title);
            location = view.findViewById(R.id.recyclerview_restaurant_grid_ubication);
        }

        public void updateRestaurantView(Restaurant restaurant, int position){
            this.restaurant = restaurant;
            this.position = position;
            Glide.with(image.getContext()).load(restaurant.getImage()).into(image);
            this.likeButton.setImageResource(AppSession.user.isFavorite(restaurant) ? R.drawable.ic_baseline_favorite_24_active : R.drawable.ic_baseline_favorite_24);
            this.title.setText(restaurant.getName());
            this.location.setText(restaurant.getUbication());
            this.likeButton.setOnClickListener(view -> likeButtonEvent(view));

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppSession.shopId = position;
                    Intent intent = new Intent(view.getContext(), ShopDetail.class);
                    view.getContext().startActivity(intent);
                }
            });
        }

        private void likeButtonEvent(View likeButtonView){
            restaurant.setLiked(!AppSession.user.isFavorite(restaurant));
            if(!restaurant.isLiked()){
                AppSession.user.removeRestaurant(restaurant);
                AppSession.user.save(likeButtonView.getContext());
            }
            else{
                AppSession.user.addFavoriteRestaurant(restaurant);
                AppSession.user.save(likeButtonView.getContext());
            }

            updateRestaurantView(restaurant, this.position);
        }
    }
}
