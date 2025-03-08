package org.skypro.skyshop.model.basket;

import java.util.ArrayList;

public class UserBasket {

    private final ArrayList<BasketItem> basket;
    private final int total;


    public UserBasket(ArrayList<BasketItem> basket) {
        this.basket = basket;
        this.total = basket.stream().mapToInt(item ->
                item.getProduct().getPriceOfProduct() * item.getCount()).sum();
    }

    public ArrayList<BasketItem> getBasket() {
        return basket;
    }

    public int getTotal() {
        return total;
    }
}
