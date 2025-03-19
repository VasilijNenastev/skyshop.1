package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.service.BasketService;
import org.skypro.skyshop.model.service.StorageService;

import java.util.*;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {
    @Mock
    private StorageService storageService;
    @Mock
    private ProductBasket productBasket;

    @InjectMocks
    private BasketService basketService;

    @Test
    //Добавление несуществующего товара в корзину//
    void testAddNonExistentProductThrowsException() {
        UUID nonExistentProductId = UUID.randomUUID();
        when(storageService.getProductById(nonExistentProductId)).thenReturn(Optional.empty());

        assertThrows(NoSuchProductException.class, () -> basketService.addProduct(nonExistentProductId),
                "должно быть выброшено NoSuchProductException");
    }

    @Test
    //Добавление существующего товара//
    void testAddExistingProductCallsBasketAddProduct() {
        UUID id = UUID.randomUUID();
        Product sugar = new SimpleProduct("сахар", id, 100);
        when(storageService.getProductById(id)).thenReturn(Optional.of(sugar));

        basketService.addProduct(id);

        verify(productBasket, times(1)).addProduct(id);
    }

    @Test
    //Тестирование пустой корзины//
    void testGetUserBasketReturnsEmptyBasket() {
        when(productBasket.getBasket()).thenReturn(Collections.emptyMap());

        UserBasket userBasket = basketService.getUserBasket();

        assertTrue(userBasket.getBasket().isEmpty(), "должно быть пусто");
    }

    @Test
    void testGetUserBasketReturnsFilledBasket() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();

        Product sugar = new SimpleProduct("сахар", id1, 100);
        Product cocoa = new SimpleProduct("какао", id2, 200);

        Map<UUID, Integer> basketForMock = new HashMap<>();
        basketForMock.put(id1, 3);
        basketForMock.put(id2, 1);

        Map<UUID, Product> uuidProductMapForMock = new LinkedHashMap<>();
        uuidProductMapForMock.put(id1, sugar);
        uuidProductMapForMock.put(id2, cocoa);

        when(productBasket.getBasket()).thenReturn(basketForMock);
        when(storageService.getProductMap()).thenReturn(uuidProductMapForMock);

       ArrayList<BasketItem> listForExpRes = new ArrayList<>();
        listForExpRes.add(new BasketItem(sugar, 3));
        listForExpRes.add(new BasketItem(cocoa, 1));

        UserBasket expRes = new UserBasket(listForExpRes);

        UserBasket actRes = basketService.getUserBasket();

        assertEquals(expRes.getTotal(), actRes.getTotal());

        String actNameProduct = expRes.getBasket().get(0).getProduct().getNameProduct();
        assertTrue(actNameProduct.equals(sugar.getNameProduct()));

        String actNameProduct1 = expRes.getBasket().get(1).getProduct().getNameProduct();
        assertTrue(actNameProduct1.equals(cocoa.getNameProduct()));


    }
}