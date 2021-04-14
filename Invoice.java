package com.example.course_work_kpz_2021;

public class Invoice {
    public String _id;
    public String name;
    public String personType;
    public String address;
    public String phone;
    public String document;
    public String bank;
    public String details;
    public String product;
    public Integer count;
    public Integer price;

    Invoice(String name,
            String personType,
            String address,
            String phone,
            String document,
            String bank,
            String details,
            String product,
            Integer count,
            Integer price
    ) {
        this.name = name;
        this.personType = personType;
        this.address = address;
        this.phone = phone;
        this.document = document;
        this.bank = bank;
        this.details = details;
        this.product = product;
        this.count = count;
        this.price = price;
    }

    Invoice(String _id,
            String name,
            String personType,
            String address,
            String phone,
            String document,
            String bank,
            String details,
            String product,
            Integer count,
            Integer price
    ) {
        this._id = _id;
        this.name = name;
        this.personType = personType;
        this.address = address;
        this.phone = phone;
        this.document = document;
        this.bank = bank;
        this.details = details;
        this.product = product;
        this.count = count;
        this.price = price;
    }
}
