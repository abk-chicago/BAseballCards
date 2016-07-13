package com.jackrocks.baseballcards;

import java.util.ArrayList;

/**
 * Created by TheDude on 7/13/16.
 */
public class ShoppingCart {



    public static ShoppingCart mInstance;
    public ArrayList<String> cards;

    ShoppingCart() {
        cards = new ArrayList();
    }

    public static synchronized ShoppingCart getInstance() {
        if (mInstance == null) {
           mInstance = new ShoppingCart();
        }
        return mInstance;
    }
}
