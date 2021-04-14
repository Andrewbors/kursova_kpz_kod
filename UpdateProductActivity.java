package com.example.course_work_kpz_2021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateProductActivity extends AppCompatActivity {

    EditText codeInput, nameInput, categoryInput;
    Button updateButton;

    String id, code, name, category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        codeInput = findViewById(R.id.updateProductCodeInput);
        nameInput = findViewById(R.id.updateProductNameInput);
        categoryInput = findViewById(R.id.updateProductCategoryInput);

        getAndSetIntentData();


        updateButton = findViewById(R.id.updateProductButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(UpdateProductActivity.this);
                code = codeInput.getText().toString().trim();
                name = nameInput.getText().toString().trim();
                category = categoryInput.getText().toString().trim();
                db.updateProduct(new Product(id, code, name, category));
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

            codeInput.setText(code);
            nameInput.setText(name);
            categoryInput.setText(category);
        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
}