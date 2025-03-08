package org.skypro.skyshop.exception;

public class PriceException extends IllegalArgumentException {

    public PriceException(){super("Цена товара не может быть меньше или равна 0!!!");}

}
