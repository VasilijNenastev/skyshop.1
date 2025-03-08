package org.skypro.skyshop.model.service;

import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;


@Service
@SessionScope
public class BasketService {

    private final ProductBasket productBasket;
    private final StorageService storageService;


    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProduct(UUID id) {
        if (!storageService.getProductById(id).isPresent()) {
            throw new NoSuchProductException();
        } else {
            productBasket.addProduct(id);
        }

    }

    public UserBasket getUserBasket() {
        ArrayList<BasketItem> basketItems = new ArrayList<>();
        Map<UUID, Integer> products = productBasket.getBasket();


        products.entrySet().forEach(v ->
                basketItems.add(new BasketItem(storageService.getProductMap().
                get(v.getKey()), v.getValue())));
        return new UserBasket(basketItems);

    }


}
