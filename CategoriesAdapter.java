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

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    Context context;
    Activity activity;
    ArrayList<String> categories;

    int position;


    CategoriesAdapter(Activity activity, Context context, ArrayList<String> categories) {
        this.activity = activity;
        this.context = context;
        this.categories = categories;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.category_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        this.position = position;

        holder.categoryRowName.setText(String.valueOf(categories.get(position)));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView categoryRowName;
        ConstraintLayout categoryRow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryRowName = itemView.findViewById(R.id.categoryRowName);
            categoryRow = itemView.findViewById(R.id.categoryRow);
        }
    }
}
