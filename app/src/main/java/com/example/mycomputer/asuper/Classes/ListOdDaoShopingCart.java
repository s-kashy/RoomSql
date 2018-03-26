package com.example.mycomputer.asuper.Classes;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.support.annotation.NonNull;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by My computer on 2/22/2018.
 */
@Dao
public interface ListOdDaoShopingCart {
    @Insert(onConflict = IGNORE)
    void addItem(ShoppingCart shoppingCart);

    @Query("DELETE FROM ShoppingCart")
    void deleteAll();

    @Query("SELECT * FROM ShoppingCart")
    List<ShoppingCart> getAllCart();
    @Query("SELECT COUNT(*) FROM ShoppingCart")
    int countNumberOfRow();
    @Query("DELETE FROM ShoppingCart where name LIKE :id ")
    void deleteByID(String id);
    @Query("SELECT SUM(amount) FROM ShoppingCart ")
    int sumOfShoppingCart();



}
