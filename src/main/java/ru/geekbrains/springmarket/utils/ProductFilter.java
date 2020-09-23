package ru.geekbrains.springmarket.utils;

import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.springmarket.entities.Product;
import ru.geekbrains.springmarket.repositories.specifications.ProductSpecifications;

import java.util.Map;

@Getter
public class ProductFilter {
    private Specification<Product> spec;
    private String filterDefinition;

    public ProductFilter(Map<String, String> params) {
        StringBuilder filterDefinitionBuilder = new StringBuilder();
        spec = Specification.where(null);

        if (params.containsKey("name") && !params.get("name").isBlank()) {
            String filterName = params.get("name");
            spec = spec.and(ProductSpecifications.nameLike(filterName));
            filterDefinitionBuilder.append("&name=").append(filterName);
        }
        if (params.containsKey("min") && !params.get("min").isBlank()) {
            Float minPrice = Float.parseFloat(params.get("min"));
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
            filterDefinitionBuilder.append("&min=").append(minPrice);
        }
        if (params.containsKey("max") && !params.get("max").isBlank()) {
            Float maxPrice = Float.parseFloat(params.get("max"));
            spec = spec.and(ProductSpecifications.priceLesserOrEqualsThan(maxPrice));
            filterDefinitionBuilder.append("&max=").append(maxPrice);
        }
        filterDefinition = filterDefinitionBuilder.toString();
    }
}
