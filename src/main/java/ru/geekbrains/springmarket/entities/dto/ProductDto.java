package ru.geekbrains.springmarket.entities.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.springmarket.entities.Product;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private Float price;
    private String categoryTitle;

    public ProductDto(Product p) {
        this.id = p.getId();
        this.title = p.getTitle();
        this.price = p.getPrice();
        this.categoryTitle = p.getCategory().getTitle();
    }
}
