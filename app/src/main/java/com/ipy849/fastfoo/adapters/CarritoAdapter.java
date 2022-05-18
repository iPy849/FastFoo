package com.ipy849.fastfoo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ipy849.fastfoo.R;
import com.ipy849.fastfoo.model.Product;

public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.CarritoHolder> {

    private View view;
    protected Product[] products;

    public CarritoAdapter(Product[] products){
        this.products = products;
    }

    @NonNull
    @Override
    public CarritoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_carrito_items, parent, false);
        return new CarritoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarritoHolder holder, int position) {
        holder.updateCarritoView(products[position]);
    }

    @Override
    public int getItemCount() {
        return products.length;
    }

    public static class CarritoHolder extends  RecyclerView.ViewHolder{
        TextView itemName;
        Button deleteItemButton;

        public CarritoHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.recyclerview_carrito_text);
            deleteItemButton = itemView.findViewById(R.id.recyclerview_carrito_eliminar);
        }

        public void updateCarritoView(Product product){
            itemName.setText(String.format("%.2f - %s", product.getPrice(), product.getName()));
            deleteItemButton.setOnClickListener(view -> deleteButtonHandler(view));
        }

        private void deleteButtonHandler(View view ){
            // TODO: Implementar el evento de click en el botón de eliminar de cada elemento del carrito
        }
    }
}
