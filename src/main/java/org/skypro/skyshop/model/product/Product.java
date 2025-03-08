package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.exception.NameException;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {

    final String nameProduct;
    private final  UUID id;

    public Product(String nameProduct, UUID id) {
        this.nameProduct = nameProduct;
        this.id = id;

        if (nameProduct == null || nameProduct.equals("") || nameProduct.isBlank()) {

            throw new NameException();

        }

    }
    public UUID getId(){
        return id;
    }



    public String getNameProduct() {

        return nameProduct;
    }


    public abstract int getPriceOfProduct();

    public abstract boolean isSpecial();


    @Override
    public String toString() {

        return nameProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(nameProduct, product.nameProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nameProduct);
    }

    @JsonIgnore
    public String getSearchTerm() {
        return getNameProduct() + " : " + getSearchContent();
    }

    @Override
    @JsonIgnore
    public String getSearchContent() {
        return "PRODUCT";
    }


    public String getStringRepresentation() {
        return getNameProduct() + " : " + getSearchContent();
    }
}


