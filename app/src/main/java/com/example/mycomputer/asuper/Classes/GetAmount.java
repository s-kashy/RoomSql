package com.example.mycomputer.asuper.Classes;

import java.util.List;

/**
 * Created by My computer on 2/25/2018.
 */

public class GetAmount {
    public static int getTotal(List<ShoppingCart> list) {
        int amount = 0;
        for (ShoppingCart s : list) {

            amount += s.getAmount();
        }
        return amount;
    }
}
