package com.example.mycomputer.asuper.Classes;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

/**
 * Created by My computer on 2/22/2018.
 */
@Database(entities = {Product.class,ShoppingCart.class,Category.class}, version = 1,exportSchema=false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract ListofDaoProduct listofDaoProduct();
   public abstract ListOfDaoCategory listOfDaoCategory();
   public abstract ListOdDaoShopingCart listOdDaoShopingCart();
    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "supermarket")
                    // allow queries on the main thread.
                    // Don't do this on a real app! See PersistenceBasicSample for an example.

                      .fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
    public static void destroyInstance() {
        INSTANCE = null;
    }
}
