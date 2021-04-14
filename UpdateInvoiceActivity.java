package com.example.course_work_kpz_2021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateInvoiceActivity extends AppCompatActivity {

    EditText
            invoice_name_input,
            invoice_address_input,
            invoice_phone_input,
            invoice_document_input,
            invoice_bank_input,
            invoice_details_input,
            invoice_product_input,
            invoice_count_input,
            invoice_price_input;

    String id, name, personType, address, phone, document, bank, details, product;
    Integer count, price;

    Button update_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_invoice);

        invoice_name_input = findViewById(R.id.invoiceUpdNameInput);
        invoice_address_input = findViewById(R.id.invoiceUpdAddressInput);
        invoice_phone_input = findViewById(R.id.invoiceUpdPhoneInput);
        invoice_document_input = findViewById(R.id.invoiceUpdDocumentInput);
        invoice_bank_input = findViewById(R.id.invoiceUpdBankInput);
        invoice_details_input = findViewById(R.id.invoiceUpdDetailsInput);
        invoice_product_input = findViewById(R.id.invoiceUpdProductInput);
        invoice_count_input = findViewById(R.id.invoiceUpdCountInput);
        invoice_price_input = findViewById(R.id.invoiceUpdPriceInput);

        Spinner spinner = (Spinner) findViewById(R.id.invoiceUpdPersonTypeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.person_type_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        getAndSetIntentData();

        update_button = findViewById(R.id.updateInvoiceButton);
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(UpdateInvoiceActivity.this);
                String name = invoice_name_input.getText().toString().trim();
                String personType = spinner.getSelectedItem().toString();
                String address = invoice_address_input.getText().toString().trim();
                String phone = invoice_phone_input.getText().toString().trim();
                String document = invoice_document_input.getText().toString().trim();
                String bank = invoice_bank_input.getText().toString().trim();
                String details = invoice_details_input.getText().toString().trim();
                String product = invoice_product_input.getText().toString().trim();
                String count = invoice_count_input.getText().toString().trim();
                Integer countFinal = new Integer(count).intValue();
                String price = invoice_price_input.getText().toString().trim();
                Integer priceFinal = new Integer(price).intValue();

                db.updateInvoice(new Invoice(id, name, personType, address, phone, document, bank, details, product, countFinal, priceFinal));
            }
        });
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") &&
                getIntent().hasExtra("count") &&
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

            invoice_name_input.setText(name);
            invoice_address_input.setText(address);
            invoice_phone_input.setText(phone);
            invoice_document_input.setText(document);
            invoice_bank_input.setText(bank);
            invoice_details_input.setText(details);
            invoice_product_input.setText(product);
            invoice_count_input.setText(String.valueOf(count));
            invoice_price_input.setText(String.valueOf(price));
        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
}