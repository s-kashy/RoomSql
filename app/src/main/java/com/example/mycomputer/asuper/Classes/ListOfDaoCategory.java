package com.example.mycomputer.asuper.Classes;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by My computer on 2/22/2018.
 */
@Dao
public interface ListOfDaoCategory {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     void insert(List<Category>categories);
    @Query("DELETE FROM category")
    void deleteAll();
    @Query("SELECT * FROM category")
    List<Category> getAll();
}
