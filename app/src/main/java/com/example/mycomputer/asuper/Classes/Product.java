package com.example.mycomputer.asuper.Classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by My computer on 2/22/2018.
 */


@Entity(tableName = "product")

@ForeignKey(entity = Category.class, parentColumns = "nameCategory", childColumns = "nameOfProduct", onDelete = CASCADE, onUpdate = CASCADE)
public class Product implements Serializable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "nameOfProduct")
    private String nameOfProduct;

    @ColumnInfo(name = "price")
    private int price;
    @ColumnInfo(name = "nameCategory")
    private String name;
    @ColumnInfo(name = "unit")
    private String unit;

    public Product() {
    }

    public void setNameOfProduct(String nameOfProduct) {
        this.nameOfProduct = nameOfProduct;
    }

    public void setPrice(int  price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getNameOfProduct() {
        return nameOfProduct;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }
}

