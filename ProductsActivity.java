package com.example.course_work_kpz_2021;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addButton;

    DatabaseHelper db;
    ArrayList<Product> products;

    ProductsAdapter productsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        recyclerView = findViewById(R.id.productsList);
        addButton = findViewById(R.id.addProductButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductsActivity.this, AddProductActivity.class);
                startActivity(intent);
            }
        });

        db = new DatabaseHelper(ProductsActivity.this);
        products = new ArrayList<Product>();

        storeDataInArrays();

        productsAdapter = new ProductsAdapter(ProductsActivity.this,ProductsActivity.this, products);
        recyclerView.setAdapter(productsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ProductsActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    void storeDataInArrays() {
        Cursor cursor = db.getAllProducts();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                String _id = cursor.getString(0);
                String code = cursor.getString(1);
                String name = cursor.getString(2);
                String category = cursor.getString(3);

                products.add(new Product(_id, code, name, category));
            }
        }
    }
}   