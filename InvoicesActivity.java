package com.example.course_work_kpz_2021;

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

public class InvoicesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    DatabaseHelper db;
    ArrayList<Invoice> invoices;

    InvoicesAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoices);

        recyclerView = findViewById(R.id.invoicesList);
        add_button = findViewById(R.id.addInvoiceButton);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InvoicesActivity.this, AddInvoiceActivity.class);
                startActivity(intent);
            }
        });

        db = new DatabaseHelper(InvoicesActivity.this);
        invoices = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new InvoicesAdapter(InvoicesActivity.this, InvoicesActivity.this, invoices);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(InvoicesActivity.this));
    }

    void storeDataInArrays() {
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
        }
    }
}