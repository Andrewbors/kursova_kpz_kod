package com.example.course_work_kpz_2021;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.ContentView;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static DatabaseHelper sInstance;
    private static final String DATABASE_NAME = "Accounting.db";
    private static final int DATABASE_VERSION = 1;

    // Table names
    private static final String TABLE_INVOICES = "invoices";
    private static final String TABLE_PRODUCTS = "products";

    // Invoice table columns
    private static final String KEY_INVOICE_ID = "_id";
    private static final String KEY_INVOICE_NAME = "invoice_name";
    private static final String KEY_INVOICE_PERSON_TYPE = "invoice_person_type";
    private static final String KEY_INVOICE_ADDRESS = "invoice_address";
    private static final String KEY_INVOICE_PHONE = "invoice_phone";
    private static final String KEY_INVOICE_DOCUMENT = "invoice_document";
    private static final String KEY_INVOICE_BANK = "invoice_bank";
    private static final String KEY_INVOICE_DETAILS = "invoice_details";
    private static final String KEY_INVOICE_PRODUCT = "invoice_product";
    private static final String KEY_INVOICE_COUNT = "invoice_count";
    private static final String KEY_INVOICE_PRICE = "invoice_price";


    // Product table columns
    private static final String KEY_PRODUCT_ID = "_id";
    private static final String KEY_PRODUCT_CODE = "product_code";
    private static final String KEY_PRODUCT_NAME = "product_name";
    private static final String KEY_PRODUCT_CATEGORY = "product_category";

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

//        context.deleteDatabase(DATABASE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_INVOICE_TABLE = "CREATE TABLE " + TABLE_INVOICES +
                " (" +
                    KEY_INVOICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_INVOICE_NAME + " TEXT, " +
                    KEY_INVOICE_PERSON_TYPE + " TEXT, " +
                    KEY_INVOICE_ADDRESS + " TEXT, " +
                    KEY_INVOICE_PHONE + " TEXT, " +
                    KEY_INVOICE_DOCUMENT + " TEXT, " +
                    KEY_INVOICE_BANK + " TEXT, " +
                    KEY_INVOICE_DETAILS + " TEXT, " +
                    KEY_INVOICE_PRODUCT + " TEXT, " +
                    KEY_INVOICE_COUNT + " INTEGER, " +
                    KEY_INVOICE_PRICE + " INTEGER" +
                ");";

        String CREATE_PRODUCT_TABLE = "CREATE TABLE " + TABLE_PRODUCTS +
                " (" +
                    KEY_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_PRODUCT_CODE + " TEXT, " +
                    KEY_PRODUCT_NAME + " TEXT," +
                    KEY_PRODUCT_CATEGORY + " TEXT" +
                ");";

        db.execSQL(CREATE_INVOICE_TABLE);
        db.execSQL(CREATE_PRODUCT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVOICES);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
            onCreate(db);
        }
    }

    void addInvoice(Invoice invoice) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_INVOICE_NAME, invoice.name);
        cv.put(KEY_INVOICE_PERSON_TYPE, invoice.personType);
        cv.put(KEY_INVOICE_ADDRESS, invoice.address);
        cv.put(KEY_INVOICE_PHONE, invoice.phone);
        cv.put(KEY_INVOICE_DOCUMENT, invoice.document);
        cv.put(KEY_INVOICE_BANK, invoice.bank);
        cv.put(KEY_INVOICE_DETAILS, invoice.details);
        cv.put(KEY_INVOICE_PRODUCT, invoice.product);
        cv.put(KEY_INVOICE_COUNT, invoice.count);
        cv.put(KEY_INVOICE_PRICE, invoice.price);

        long result = db.insert(TABLE_INVOICES,null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_PRODUCT_CODE, product.code);
        cv.put(KEY_PRODUCT_NAME, product.name);
        cv.put(KEY_PRODUCT_CATEGORY, product.category);

        long result = db.insert(TABLE_PRODUCTS,null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor getAllInvoices() {
        String query = "SELECT * FROM " + TABLE_INVOICES;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    Cursor getAllProducts() {
        String query = "SELECT * FROM " + TABLE_PRODUCTS;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    void updateProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_PRODUCT_CODE, product.code);
        cv.put(KEY_PRODUCT_NAME, product.name);
        cv.put(KEY_PRODUCT_CATEGORY, product.category);

        long result = db.update(TABLE_PRODUCTS, cv,"_id=?", new String[]{ product._id });
        if (result == -1) {
            Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Updated successfully", Toast.LENGTH_SHORT).show();
        }
    }

    void updateInvoice(Invoice invoice) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_INVOICE_NAME, invoice.name);
        cv.put(KEY_INVOICE_PERSON_TYPE, invoice.personType);
        cv.put(KEY_INVOICE_ADDRESS, invoice.address);
        cv.put(KEY_INVOICE_PHONE, invoice.phone);
        cv.put(KEY_INVOICE_DOCUMENT, invoice.document);
        cv.put(KEY_INVOICE_BANK, invoice.bank);
        cv.put(KEY_INVOICE_DETAILS, invoice.details);
        cv.put(KEY_INVOICE_PRODUCT, invoice.product);
        cv.put(KEY_INVOICE_COUNT, invoice.count);
        cv.put(KEY_INVOICE_PRICE, invoice.price);

        long result = db.update(TABLE_INVOICES, cv,"_id=?", new String[]{ invoice._id });
        if (result == -1) {
            Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Updated successfully", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneProduct(String product_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCTS, "_id=?", new String[]{ product_id });
    }

    void deleteOneInvoice(String invoice_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_INVOICES, "_id=?", new String[]{ invoice_id });
    }
}
