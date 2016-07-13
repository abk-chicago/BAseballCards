package com.jackrocks.baseballcards;

import java.util.ArrayList;

/**
 * Created by TheDude on 7/13/16.
 */
public class ShoppingCart extends ArrayList<String> {

    private static ShoppingCart mInstance = new ShoppingCart();

    public static synchronized ShoppingCart getInstance() {
        if (mInstance == null) {
           mInstance = new ShoppingCart();
        }
        return mInstance;
    }
}
