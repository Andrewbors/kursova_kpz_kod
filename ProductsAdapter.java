package com.example.course_work_kpz_2021;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    Context context;
    Activity activity;
    ArrayList<Product> products;

    int position;


    ProductsAdapter(Activity activity, Context context, ArrayList<Product> products) {
        this.activity = activity;
        this.context = context;
        this.products = products;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.product_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        this.position = position;

        holder.productRowCode.setText(String.valueOf(products.get(position).code));
        holder.productRowName.setText(String.valueOf(products.get(position).name));
        holder.productRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                Toast.makeText(context, products.get(position).code, Toast.LENGTH_SHORT).show();
                intent.putExtra("id", String.valueOf(products.get(position)._id));
                intent.putExtra("code", String.valueOf(products.get(position).code));
                intent.putExtra("name", String.valueOf(products.get(position).name));
                intent.putExtra("category", String.valueOf(products.get(position).category));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView productRowName, productRowCode;
        ConstraintLayout productRow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productRowName = itemView.findViewById(R.id.productRowName);
            productRowCode = itemView.findViewById(R.id.productRowCode);
            productRow = itemView.findViewById(R.id.productRow);
        }
    }
}
