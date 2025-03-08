package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {

    String getSearchTerm();

    String getSearchContent();

    default String getStringRepresentation() {
        return getSearchTerm() + " - " + getSearchContent();
    }

    UUID getId();
}

