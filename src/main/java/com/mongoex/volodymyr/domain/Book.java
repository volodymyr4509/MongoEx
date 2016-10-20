package com.mongoex.volodymyr.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A Book.
 */

@Document(collection = "book")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("index")
    private Integer index;

    @Field("guid")
    private String guid;

    @Field("isActive")
    private Boolean isActive;

    @Field("price")
    private String price;

    @Field("name")
    private String name;

    @Field("seller")
    private Seller seller;

    @Field("tags")
    private List<String> tags;

    @Field("author")
    private Author author;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIndex() {
        return index;
    }

    public Book index(Integer index) {
        this.index = index;
        return this;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getGuid() {
        return guid;
    }

    public Book guid(String guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public Book isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getPrice() {
        return price;
    }

    public Book price(String price) {
        this.price = price;
        return this;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Book name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Seller getSeller() {
        return seller;
    }

    public Book seller(Seller seller) {
        this.seller = seller;
        return this;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public List<String> getTags() {
        return tags;
    }

    public Book tags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Author getAuthor() {
        return author;
    }

    public Book author(Author author) {
        this.author = author;
        return this;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        if(book.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Book{" +
            "id=" + id +
            ", index='" + index + "'" +
            ", guid='" + guid + "'" +
            ", isActive='" + isActive + "'" +
            ", price='" + price + "'" +
            ", name='" + name + "'" +
            ", seller='" + seller + "'" +
            ", tags='" + tags + "'" +
            ", author='" + author + "'" +
            '}';
    }
}
