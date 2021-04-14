package com.example.course_work_kpz_2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProductDetailsActivity extends AppCompatActivity {
    String id, code, name, category;

    TextView codeView, nameView, categoryView;
    Button toUpdateButton, deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        codeView = findViewById(R.id.productDetailsCode);
        nameView = findViewById(R.id.productDetailsName);
        categoryView = findViewById(R.id.productDetailsCategory);

        toUpdateButton = findViewById(R.id.productDetailsToUpdateButton);
        deleteButton = findViewById(R.id.productDetailsDeleteButton);

        getAndSetIntentData();

        toUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailsActivity.this, UpdateProductActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("code", code);
                intent.putExtra("name", name);
                intent.putExtra("category", category);

                startActivityForResult(intent, 1);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(ProductDetailsActivity.this);
                db.deleteOneProduct(id);

                Intent intent = new Intent(ProductDetailsActivity.this, ProductsActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") &&
                getIntent().hasExtra("code") &&
                getIntent().hasExtra("name")) {
            id = getIntent().getStringExtra("id");
            code = getIntent().getStringExtra("code");
            name = getIntent().getStringExtra("name");
            category = getIntent().getStringExtra("category");

            codeView.setText(code);
            nameView.setText(name);
            categoryView.setText(category);
        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
}