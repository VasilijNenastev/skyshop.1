package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class SearchService {

    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Map<UUID, SearchResult> search(String pattern) {
        Map<UUID, Searchable> searchables = storageService.getSearchables();
        Map<UUID, SearchResult> searchResults = new HashMap<>();
        for(Searchable searchable : searchables.values()) {
            if (searchable.getSearchTerm().contains(pattern)) {
                searchResults.put(searchable.getId(), SearchResult.fromSearchable(searchable));
            }
        }
        return searchResults;
    }
}
