package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class StorageService {

    private final Map<UUID, Product> productMap;
    private final Map<UUID, Article> articleMap;

    public StorageService(Map<UUID, Product> productMap, Map<UUID, Article> articleMap) {
        this.productMap = productMap;
        this.articleMap = articleMap;


        testData();
        System.out.println(productMap.values());
        System.out.println(articleMap.values());
    }


    public Map<UUID, Product> getProductMap() {
        return productMap;
    }

    public Map<UUID, Article> getArticleMap() {
        return articleMap;
    }

    private void testData() {
        SimpleProduct product1 = new SimpleProduct("сахар", UUID.randomUUID(), 700);
        DiscountedProduct product2 = new DiscountedProduct("молоко", UUID.randomUUID(), 200, 11);
        FixPriceProduct product3 = new FixPriceProduct("кофе", UUID.randomUUID());
        SimpleProduct product4 = new SimpleProduct("конфеты", UUID.randomUUID(), 500);
        SimpleProduct product5 = new SimpleProduct("колбаса", UUID.randomUUID(), 600);
        SimpleProduct product6 = new SimpleProduct("мясо", UUID.randomUUID(), 2000);
        SimpleProduct product7 = new SimpleProduct("молоко", UUID.randomUUID(), 300);


        productMap.put(product1.getId(), product1);
        productMap.put(product2.getId(), product2);
        productMap.put(product3.getId(), product3);
        productMap.put(product4.getId(), product4);
        productMap.put(product5.getId(), product5);
        productMap.put(product6.getId(), product6);
        productMap.put(product7.getId(), product7);

        Article article1 = new Article("Название статьи 1", "Текст статьи 1", UUID.randomUUID());
        Article article2 = new Article("Название статьи 2", "Текст статьи 2", UUID.randomUUID());
        Article article3 = new Article("Название статьи 3", "Текст статьи 3", UUID.randomUUID());
        Article article4 = new Article("Название статьи 4", "Текст статьи 4", UUID.randomUUID());

        articleMap.put(article1.getId(), article1);
        articleMap.put(article2.getId(), article2);
        articleMap.put(article3.getId(), article3);
        articleMap.put(article4.getId(), article4);
    }


    public Map<UUID, Searchable> getSearchables() {
        Map<UUID, Searchable> searchables = new HashMap<>();
        int i = 0;
        for (Product product : productMap.values()) {
            searchables.put(product.getId(), product);
            i++;
        }
        for (Article article : articleMap.values()) {
            searchables.put(article.getId(), article);
            i++;
        }
        return searchables;
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(productMap.get(id));
    }


}




