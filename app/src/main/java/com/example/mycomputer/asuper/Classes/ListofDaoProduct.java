package com.example.mycomputer.asuper.Classes;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by My computer on 2/22/2018.
 */
@Dao
public interface ListofDaoProduct {
    @Query("SELECT * FROM product")
    List<Product> getAll();

    @Query("SELECT * FROM product where nameCategory LIKE :nameCategory")
    List<Product> getAllProductById(String nameCategory);

    @Query("DELETE FROM product")
    void deleteAll();


    @Insert(onConflict = REPLACE)
    void insertProdect(Product product);

    @Query("SELECT COUNT(*) FROM product")
    int countNumberOfRow();

    @Update
    void updateProdce(Product product);


}
