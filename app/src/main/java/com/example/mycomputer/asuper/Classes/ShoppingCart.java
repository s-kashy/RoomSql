package com.example.mycomputer.asuper.Classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by My computer on 2/22/2018.
 */

@Entity(tableName = "ShoppingCart")
@ForeignKey(entity = Product.class, parentColumns = "nameOfProduct", childColumns = "name", onDelete = CASCADE, onUpdate = CASCADE)
public class ShoppingCart implements Serializable {
  @PrimaryKey
  @NonNull
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "amount")
    private int amount;


    public ShoppingCart(String name, int amount) {
        this.name = name;
        this.amount = amount;

    }

    @Ignore
    public ShoppingCart() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }


}
