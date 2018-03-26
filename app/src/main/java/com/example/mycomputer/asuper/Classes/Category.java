package com.example.mycomputer.asuper.Classes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by My computer on 2/22/2018.
 */

@Entity(tableName = "category")
public class Category implements Serializable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name ="nameOfCategory")
    private String nameOfCategory;


    public Category(String cat) {
        this.nameOfCategory = cat;
    }

    public Category() {
    }

    public String getNameOfCategory() {
        return nameOfCategory;
    }

    public void setNameOfCategory(String nameOfCategory) {
        this.nameOfCategory = nameOfCategory;
    }
}
