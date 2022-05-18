package com.ipy849.fastfoo.adapters;

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
        holder.updateRestaurantView(this.restaurantsData.get(position));

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

        public RestaurantViewholder(@NonNull View view) {
            super(view);
            image = view.findViewById(R.id.recyclerview_restaurant_grid_image);
            likeButton = view.findViewById(R.id.recyclerview_restaurant_grid_like);
            title = view.findViewById(R.id.recyclerview_restaurant_grid_title);
            location = view.findViewById(R.id.recyclerview_restaurant_grid_ubication);

            likeButton.setOnClickListener(likeButtonView -> likeButtonEvent(likeButtonView));
        }

        public void updateRestaurantView(Restaurant restaurant){
            Glide.with(image.getContext()).load(restaurant.getImage()).into(image);
            this.likeButton.setImageResource(restaurant.isLiked() ? R.drawable.ic_baseline_favorite_24_active : R.drawable.ic_baseline_favorite_24);
            this.title.setText(restaurant.getTitle());
            this.location.setText(restaurant.getUbication());
        }

        private void likeButtonEvent(View likeButtonView){
            // TODO: Implementar el evento de click en el botón de la tarjeta
        }
    }
}
