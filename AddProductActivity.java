package com.example.course_work_kpz_2021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddProductActivity extends AppCompatActivity {

    EditText productCodeInput, productNameInput, productCategoryInput;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        productCodeInput = findViewById(R.id.productCodeInput);
        productNameInput = findViewById(R.id.productNameInput);
        productCategoryInput = findViewById(R.id.productCategoryInput);

        addButton = findViewById(R.id.addProductButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(AddProductActivity.this);
                String code = productCodeInput.getText().toString().trim();
                String name = productNameInput.getText().toString().trim();
                String category = productCategoryInput.getText().toString().trim();
                db.addProduct(new Product(code, name, category));
            }
        });
    }
}