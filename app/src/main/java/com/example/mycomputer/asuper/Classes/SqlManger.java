package com.example.mycomputer.asuper.Classes;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by My computer on 2/22/2018.
 */

public class SqlManger implements ISqlManger {
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void getAllProduct(Context context, Consumer<List<Product>> callback) {

        AppDatabase appDatabase = AppDatabase.getAppDatabase(context);
        List<Product> products;
        products = appDatabase.listofDaoProduct().getAll();
        if (!products.isEmpty()) {
            callback.accept(products);
        } else {
            callback.accept(null);
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void deleteAll(Consumer<Boolean> callback, Context context) {
//        AppDatabase appDatabase;
//        appDatabase=AppDatabase.getAppDatabase(context);
//        appDatabase.listofDaoProduct().deleteAll();
//        appDatabase.listOfDaoCategory().deleteAll();
//        appDatabase.listOdDaoShopingCart().deleteAll();
//        callback.accept(true);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void getAllProductById(String id, Context context, Consumer<List<Product>> callback) {
        AppDatabase appDatabase = AppDatabase.getAppDatabase(context);

        List<Product> products = new ArrayList<>();
        products = appDatabase.listofDaoProduct().getAllProductById(id);
        callback.accept(products);


    }

    @Override
    public void getSumOfProduct(Context context, Consumer<Integer> callback) {
        AppDatabase appDatabase=AppDatabase.getAppDatabase(context);
       // Integer sum=(Integer) appDatabase.listOdDaoShopingCart().g
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void getAllShoppingCart(Context context, Consumer<List<ShoppingCart>> callback) {
        AppDatabase appDatabase = AppDatabase.getAppDatabase(context);
        List<ShoppingCart> shoppingCarts ;
        shoppingCarts=appDatabase.listOdDaoShopingCart().getAllCart();
        callback.accept(shoppingCarts);

    }

}
