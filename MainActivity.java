package com.example.course_work_kpz_2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toProducts(View view) {
        navigator("PRODUCTS");
    }

    public void toInvoices(View view) {
        navigator("INVOICES");
    }

    public void toRequests(View view) {
        navigator("REQUESTS");
    }

    public void navigator(String direction) {
        Intent intent;
        switch (direction) {
            case "PRODUCTS":
                intent = new Intent(this, ProductsActivity.class);
                break;
            case "INVOICES":
                intent = new Intent(this, InvoicesActivity.class);
                break;
            case "REQUESTS":
                intent = new Intent(this, RequestsActivity.class);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + direction);
        }

        startActivity(intent);
    }
}