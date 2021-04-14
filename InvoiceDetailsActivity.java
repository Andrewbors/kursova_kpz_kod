package com.example.course_work_kpz_2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class InvoiceDetailsActivity extends AppCompatActivity {
    String id, name, personType, address, phone, document, bank, details, product;
    Integer count, price;

    TextView nameView, personTypeView, addressView, phoneView, documentView, bankView, detailsView, productView, countView, priceForOneView, priceForAllView;
    Button toUpdateButton, deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_details);

        nameView = findViewById(R.id.invoiceDetailsName);
        addressView = findViewById(R.id.invoiceDetailsAddress);
        personTypeView = findViewById(R.id.invoiceDetailsPersonType);
        phoneView = findViewById(R.id.invoiceDetailsPhone);
        documentView = findViewById(R.id.invoiceDetailsDocument);
        bankView = findViewById(R.id.invoiceDetailsBank);
        detailsView = findViewById(R.id.invoiceDetailsDetails);
        productView = findViewById(R.id.invoiceDetailsProduct);
        countView = findViewById(R.id.invoiceDetailsCount);
        priceForOneView = findViewById(R.id.invoiceDetailsPriceForOne);
        priceForAllView = findViewById(R.id.invoiceDetailsPriceForAll);

        toUpdateButton = findViewById(R.id.invoiceDetailsToUpdateButton);
        deleteButton = findViewById(R.id.invoiceDetailsDeleteButton);

        getAndSetIntentData();

        toUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InvoiceDetailsActivity.this, UpdateInvoiceActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("name", name);
                intent.putExtra("personType", personType);
                intent.putExtra("address", address);
                intent.putExtra("phone", phone);
                intent.putExtra("document", document);
                intent.putExtra("bank", bank);
                intent.putExtra("details", details);
                intent.putExtra("product", product);
                intent.putExtra("count", count);
                intent.putExtra("price", price);

                startActivityForResult(intent, 0);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(InvoiceDetailsActivity.this);
                db.deleteOneInvoice(id);

                Intent intent = new Intent(InvoiceDetailsActivity.this, InvoicesActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") &&
                getIntent().hasExtra("address") &&
                getIntent().hasExtra("name")) {
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            address = getIntent().getStringExtra("address");
            phone = getIntent().getStringExtra("phone");
            document = getIntent().getStringExtra("document");
            bank = getIntent().getStringExtra("bank");
            details = getIntent().getStringExtra("details");
            product = getIntent().getStringExtra("product");
            count = getIntent().getIntExtra("count", 1);
            price = getIntent().getIntExtra("price", 0);

            nameView.setText(name);
            addressView.setText(address);
            phoneView.setText(phone);
            documentView.setText(document);
            bankView.setText(bank);
            detailsView.setText(details);
            productView.setText(product);
            countView.setText(String.valueOf(count));
            priceForOneView.setText(String.valueOf(price));
            priceForAllView.setText(String.valueOf(price * count));
        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
}