package com.ipy849.fastfoo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ipy849.fastfoo.AppSession;
import com.ipy849.fastfoo.R;
import com.ipy849.fastfoo.model.Product;

import java.util.ArrayList;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopHolder> {

    private View view;
    protected ArrayList<Product> products;

    public ShopAdapter(ArrayList<Product> products){
        this.products = products;
    }

    @NonNull
    @Override
    public ShopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_shop_items, parent, false);
        return new ShopHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopHolder holder, int position) {
        holder.updateCarritoView(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ShopHolder extends RecyclerView.ViewHolder{
        TextView itemName;
        Button deleteItemButton;
        Product product;

        public ShopHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.recyclerview_shop_text);
            deleteItemButton = itemView.findViewById(R.id.recyclerview_shop_agregar);
        }

        public void updateCarritoView(Product product){
            this.product = product;
            itemName.setText(String.format("%.2f - %s", product.getPrice(), product.getName()));
            deleteItemButton.setOnClickListener(view -> agregarButtonHandler(view));
        }

        private void agregarButtonHandler(View view ){
            AppSession.user.addProduct2Cart(product);
            AppSession.user.save(view.getContext());
        }
    }
}
