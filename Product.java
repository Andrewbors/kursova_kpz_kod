package com.example.course_work_kpz_2021;

public class Product {
    public String _id;
    public String code;
    public String name;
    public String category;

    Product(String code, String name, String category) {
        this.code = code;
        this.name = name;
        this.category = category;
    }

    Product(String _id, String code, String name, String category) {
        this._id = _id;
        this.code = code;
        this.name = name;
        this.category = category;
    }
}
