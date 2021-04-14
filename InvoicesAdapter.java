package com.example.course_work_kpz_2021;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class InvoicesAdapter extends RecyclerView.Adapter<InvoicesAdapter.ViewHolder> {

    Context context;
    Activity activity;
    ArrayList<Invoice> invoices;


    InvoicesAdapter(Activity activity, Context context, ArrayList<Invoice> invoices) {
        this.activity = activity;
        this.context = context;
        this.invoices = invoices;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.invoice_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.invoiceRowCode.setText(String.valueOf(invoices.get(position).personType));
        holder.invoiceRowName.setText(String.valueOf(invoices.get(position).name));
        holder.invoiceRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InvoiceDetailsActivity.class);
                intent.putExtra("id", String.valueOf(invoices.get(position)._id));
                intent.putExtra("name", String.valueOf(invoices.get(position).name));
                intent.putExtra("personType", String.valueOf(invoices.get(position).personType));
                intent.putExtra("address", String.valueOf(invoices.get(position).address));
                intent.putExtra("phone", String.valueOf(invoices.get(position).phone));
                intent.putExtra("document", String.valueOf(invoices.get(position).document));
                intent.putExtra("bank", String.valueOf(invoices.get(position).bank));
                intent.putExtra("details", String.valueOf(invoices.get(position).details));
                intent.putExtra("product", String.valueOf(invoices.get(position).product));
                intent.putExtra("count", new Integer(invoices.get(position).count).intValue());
                intent.putExtra("price", new Integer(invoices.get(position).price).intValue());

                activity.startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return invoices.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView invoiceRowName, invoiceRowCode;
        ConstraintLayout invoiceRow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            invoiceRowName = itemView.findViewById(R.id.invoiceRowName);
            invoiceRowCode = itemView.findViewById(R.id.invoiceRowCode);
            invoiceRow = itemView.findViewById(R.id.invoiceRow);
        }
    }
}
