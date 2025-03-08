package org.skypro.skyshop.model.search;

import java.util.UUID;

public class SearchResult {

    public final UUID id;
    public final String name;
    public final String contentType;

    public SearchResult(UUID id, String name, String contentType) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContentType() {
        return contentType;
    }

    public static SearchResult fromSearchable(Searchable searchable){
        return new SearchResult(searchable.getId(), searchable.getStringRepresentation(),
                searchable.getSearchContent());
    }

}
