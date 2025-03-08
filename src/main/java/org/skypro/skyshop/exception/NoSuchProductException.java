package org.skypro.skyshop.exception;

public class NoSuchProductException extends IllegalArgumentException {
    public NoSuchProductException() {

        super("Ошибка 404! Нет такого продукта!!!");
    }
}
