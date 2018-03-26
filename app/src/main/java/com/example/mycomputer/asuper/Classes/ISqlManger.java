package com.example.mycomputer.asuper.Classes;

import android.content.Context;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by My computer on 2/22/2018.
 */

public interface ISqlManger {
    void getAllProduct(Context context, Consumer<List<Product>> callback);

    void deleteAll(Consumer<Boolean> callback, Context context);

    void getAllProductById(String id, Context context, Consumer<List<Product>> callback);

    void getSumOfProduct(Context context, Consumer<Integer> callback);

    void getAllShoppingCart(Context context, Consumer<List<ShoppingCart>> callback);
}
