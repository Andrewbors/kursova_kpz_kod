package com.example.course_work_kpz_2021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RequestsActivity extends AppCompatActivity {

    DatabaseHelper db;
    ArrayList<Invoice> invoices = new ArrayList<>();
    RecyclerView recyclerView;
    InvoicesAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reqeusts);

        db = new DatabaseHelper(RequestsActivity.this);
        recyclerView = findViewById(R.id.requestsRecycler);
    }

    public void onClickFirst(View view) {
        Cursor cursor = db.getAllInvoices();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                String _id = cursor.getString(0);
                String name = cursor.getString(1);
                String personType = cursor.getString(2);
                String address = cursor.getString(3);
                String phone = cursor.getString(4);
                String document = cursor.getString(5);
                String bank = cursor.getString(6);
                String details = cursor.getString(7);
                String product = cursor.getString(8);
                Integer count = cursor.getInt(9);
                Integer price = cursor.getInt(10);

                invoices.add(new Invoice(_id, name, personType, address, phone, document, bank, details, product, count, price));
            }

            Invoice max = invoices.get(0);
            for (int i = 0; i < invoices.size(); i++) {
                if (max.price < invoices.get(i).price) {
                    max = invoices.get(i);
                }
            }

            ArrayList<Invoice> list = new ArrayList<>();
            list.add(max);

            customAdapter = new InvoicesAdapter(RequestsActivity.this, RequestsActivity.this, list);
            recyclerView.setAdapter(customAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(RequestsActivity.this));
        }
    }

    public void onClickSecond(View view) {
        Cursor cursor = db.getAllProducts();
        ArrayList<String> categories = new ArrayList<>();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                String category = cursor.getString(3);

                if (!categories.contains(category)) {
                    categories.add(category);
                }
            }

            CategoriesAdapter ctAdapter = new CategoriesAdapter(RequestsActivity.this, RequestsActivity.this, categories);
            recyclerView.setAdapter(ctAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(RequestsActivity.this));
        }
    }
}