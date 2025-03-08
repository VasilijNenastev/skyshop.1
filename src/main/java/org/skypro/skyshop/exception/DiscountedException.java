package org.skypro.skyshop.exception;

public class DiscountedException extends IllegalArgumentException {

    public DiscountedException (){super("Скидка не может быть менее 0% или более 100%");}

}
