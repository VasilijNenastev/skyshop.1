package org.skypro.skyshop.controller;

import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.service.BasketService;
import org.skypro.skyshop.model.service.SearchService;
import org.skypro.skyshop.model.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@RestController
public class ShopController {
    private final StorageService storageService;
    private final SearchService searchService;
    private final BasketService basketService;


    public ShopController(StorageService storageService, SearchService searchService, BasketService basketService) {
        this.storageService = storageService;
        this.searchService = searchService;
        this.basketService = basketService;
    }

    @GetMapping("/products")
    public Collection<Product> getAllProducts() {
        return storageService.getProductMap().values();
    }

    @GetMapping("/articles")
    public Collection<Article> getAllArticles() {
        return storageService.getArticleMap().values();
    }

    @GetMapping("/search")
    public Collection<SearchResult> search(@RequestParam("pattern") String pattern) {
        return searchService.search(pattern).values();
    }

    @GetMapping("/basket/{id}")
    public String addProduct(@PathVariable("id") UUID id) {
        try {
            basketService.addProduct(id);
            return "*Продукт успешно добавлен*";
        } catch (NoSuchProductException e) {
            return e.getMessage();
        }

    }

    @GetMapping("/basket")
    public UserBasket getUserBasket() {
        return basketService.getUserBasket();
    }

}
