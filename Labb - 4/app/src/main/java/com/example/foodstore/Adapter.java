package com.example.foodstore;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private final OnProductClickListener onProductClickListener;
    private final Context context;
    private final ArrayList<Model> modelArrayList;

    //constructor
    public Adapter(Context context, ArrayList<Model> modelArrayList, OnProductClickListener onProductClickListener){
        this.context = context;
        this.modelArrayList = modelArrayList;
        this.onProductClickListener = onProductClickListener;

    }


    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_card, parent, false);
        return new ViewHolder(view, onProductClickListener);
    }
     public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position){
        Model model = modelArrayList.get(position);
        holder.name.setText(model.getName());
        holder.price.setText(model.getPrice());
     }

     public int getItemCount(){
        return modelArrayList.size();
     }

     public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView price;

        public ViewHolder(@NonNull View itemView, OnProductClickListener onProductClickListener){
            super(itemView);
            name = itemView.findViewById(R.id.txtName);
            price = itemView.findViewById(R.id.txtPrice);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onProductClickListener != null){
                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION){
                            onProductClickListener.onItemClick(position);
                        }
                    }
                }
            });
        }
     }
}
