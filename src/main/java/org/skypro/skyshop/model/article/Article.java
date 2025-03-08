package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {

    private final UUID id;
    private final String article;
    private final String text;

    public Article(String article, String text, UUID id) {
        this.article = article;
        this.text = text;
        this.id = id;
    }

    public String getArticle() {
        return article;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return article + '\n' + text;
    }

    @Override
    @JsonIgnore
    public String getSearchTerm() {
        return article + "\nТип - ARTICLE\n";
    }

    @Override
    @JsonIgnore
    public String getSearchContent() {
        return "ARTICLE\n";
    }

    @Override
    public String getStringRepresentation() {
        return article + "\nТип - ARTICLE";
    }

    @Override
    public UUID getId() {
        return id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article1 = (Article) o;
        return Objects.equals(article, article1.article) && Objects.equals(text, article1.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(article, text);
    }
}
