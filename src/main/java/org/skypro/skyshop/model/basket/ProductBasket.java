package org.skypro.skyshop.model.basket;


import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SessionScope
@Service
public class ProductBasket {

    private final Map<UUID, Integer> basket = new HashMap<>();

    public ProductBasket( ) {

    }


    public Map<UUID, Integer> getBasket() {
        return Collections.unmodifiableMap(basket);
    }

    public void addProduct(UUID idProd) {
        if (basket.containsKey(idProd)) {
            basket.put(idProd, basket.get(idProd) + 1);
        } else {
            basket.put(idProd, 1);
        }
    }

}
