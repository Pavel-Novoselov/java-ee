package ru.geekbrains.service;

import ru.geekbrains.persist.Category;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class ProductRepr implements Serializable {

    private static final long serialVersionUID = -2371005780303886795L;
    private Long id;

    @NotEmpty
    private String description;

    @NotNull
    private BigDecimal price;

    private long categoryId;

    private String categoryName;

    public ProductRepr() {
    }

    public ProductRepr(
            @NotEmpty final String description,
            @NotNull final BigDecimal price,
            final long categoryId
    ) {
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
    }

    public ProductRepr(
            final Long id,
            @NotEmpty final String description,
            @NotNull final BigDecimal price,
            Category category
    ) {
        this.id = id;
        this.description = description;
        this.price = price;
        if (category == null){
            this.categoryId =  -1L;
            this.categoryName = null;
        }
        this.categoryId = category.getId();
        this.categoryName = category.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(final long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(final String categoryName) {
        this.categoryName = categoryName;
    }
}