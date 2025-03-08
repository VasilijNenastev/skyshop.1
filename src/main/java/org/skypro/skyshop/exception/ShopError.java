package org.skypro.skyshop.exception;

public class ShopError {

    private final String code;
    private final String message;

    public ShopError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ShopError{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
