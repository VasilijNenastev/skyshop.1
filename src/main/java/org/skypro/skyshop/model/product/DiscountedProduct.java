package org.skypro.skyshop.model.product;

import org.skypro.skyshop.exception.DiscountedException;
import org.skypro.skyshop.exception.PriceException;

import java.util.UUID;

public class DiscountedProduct extends Product {

    public int basePrice;
    public int percentageDiscount;

    public DiscountedProduct(String nameProduct, UUID id, int basePrice, int percentageDiscount) {
        super(nameProduct, id);
        if (basePrice <= 0) {
            throw new PriceException();
        }
        if (percentageDiscount < 0 || percentageDiscount > 100) {
            throw new DiscountedException();
        }

        this.basePrice = basePrice;
        this.percentageDiscount = percentageDiscount;
    }


    @Override
    public int getPriceOfProduct() {
        return basePrice - ((basePrice * percentageDiscount) / 100);
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return nameProduct + " : " + basePrice + " рублей : скидка " + percentageDiscount + " %  ";
    }

}

