package org.skypro.skyshop.exception;

public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException() {

        super("Ошибка 404! Нет такого продукта!!!");
    }
}
