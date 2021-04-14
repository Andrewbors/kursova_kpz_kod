package com.example.course_work_kpz_2021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddInvoiceActivity extends AppCompatActivity {

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

    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_invoice);

        invoice_name_input = findViewById(R.id.invoiceNameInput);
        invoice_address_input = findViewById(R.id.invoiceAddressInput);
        invoice_phone_input = findViewById(R.id.invoicePhoneInput);
        invoice_document_input = findViewById(R.id.invoiceDocumentInput);
        invoice_bank_input = findViewById(R.id.invoiceBankInput);
        invoice_details_input = findViewById(R.id.invoiceDetailsInput);
        invoice_product_input = findViewById(R.id.invoiceProductInput);
        invoice_count_input = findViewById(R.id.invoiceCountInput);
        invoice_price_input = findViewById(R.id.invoicePriceInput);

        Spinner spinner = (Spinner) findViewById(R.id.invoicePersonTypeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.person_type_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        add_button = findViewById(R.id.addInvoiceButton);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(AddInvoiceActivity.this);
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

                db.addInvoice(new Invoice(name, personType, address, phone, document, bank, details, product, countFinal, priceFinal));
            }
        });
    }
}