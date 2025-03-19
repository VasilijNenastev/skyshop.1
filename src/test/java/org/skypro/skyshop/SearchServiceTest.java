package org.skypro.skyshop;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.model.service.SearchService;
import org.skypro.skyshop.model.service.StorageService;


import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @Test
    public void testSearch_When_Objects_Are_Missing_From_StorageService() {
        when(storageService.getSearchables()).thenReturn(Collections.emptyMap());
        Map<UUID, SearchResult> searchResultMap = searchService.search("PRODUCT");
        assertTrue(searchResultMap.isEmpty(),"Пусто");
    }

    @Test
    public void testSearch_If_There_Are_Objects_In_StorageService_But_There_Is_No_Suitable_One(){
        Map<UUID, Searchable> searchableMap = Map.of(UUID.randomUUID(), new SimpleProduct("Test1", UUID.randomUUID(), 400),
                UUID.randomUUID(), new Article("Test2","Content",UUID.randomUUID()));

        when(storageService.getSearchables()).thenReturn(searchableMap);
        Map<UUID, SearchResult> searchResultMap = searchService.search("NoMatchesFound");
        assertTrue(searchResultMap.isEmpty(), "Нет ни одного подходящего объекта");
    }

    @Test
    public void testSearch_When_There_Is_A_Matching_Object_In(){
       UUID matchingId = UUID.randomUUID();
       Map<UUID, Searchable> searchableMap = Map.of(matchingId,
               new SimpleProduct("Matching_Object", matchingId,500),
               UUID.randomUUID(),new Article("Unsuitable_Object", "Content", UUID.randomUUID()));
       when(storageService.getSearchables()).thenReturn(searchableMap);

       Map<UUID,SearchResult> searchResultMap = searchService.search("Matching_Object");

       assertEquals(1, searchResultMap.size(), " один подходящий объект");
       assertTrue(searchResultMap.containsKey(matchingId),"должно быть совпадение");
    }






}
