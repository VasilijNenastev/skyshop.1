package org.skypro.skyshop.model.product;

import org.skypro.skyshop.exception.PriceException;

import java.util.UUID;

public class SimpleProduct extends Product {

    final int priceOfProduct;

    public SimpleProduct(String nameProduct, UUID id, int priceOfProduct) {
        super(nameProduct, id);
        if (priceOfProduct <= 0) {
            throw new PriceException();
        }

        this.priceOfProduct = priceOfProduct;
    }

    @Override
    public int getPriceOfProduct() {
        return priceOfProduct;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return nameProduct + " : " + priceOfProduct + " рублей";
    }




}
