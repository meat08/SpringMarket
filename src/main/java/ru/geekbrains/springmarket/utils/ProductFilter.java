package ru.geekbrains.springmarket.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.springmarket.entities.Category;
import ru.geekbrains.springmarket.entities.Product;
import ru.geekbrains.springmarket.repositories.specifications.ProductSpecifications;

import java.util.List;
import java.util.Map;

@Getter
public class ProductFilter {
    private Specification<Product> spec;

    @SneakyThrows
    public ProductFilter(Map<String, String> params) {
        spec = Specification.where(null);

        if (params.containsKey("title") && !params.get("title").isBlank()) {
            String filterName = params.get("title");
            spec = spec.and(ProductSpecifications.nameLike(filterName));
        }
        if (params.containsKey("min_price") && !params.get("min_price").isBlank()) {
            Float minPrice = Float.parseFloat(params.get("min_price"));
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (params.containsKey("max_price") && !params.get("max_price").isBlank()) {
            Float maxPrice = Float.parseFloat(params.get("max_price"));
            spec = spec.and(ProductSpecifications.priceLesserOrEqualsThan(maxPrice));
        }
        if (params.containsKey("category") && !params.get("category").isBlank()) {
            ObjectMapper om = new ObjectMapper();
            List<Category> categories = om.readValue(params.get("category"), om.getTypeFactory().constructCollectionType(List.class, Category.class));
            spec = spec.and(ProductSpecifications.categoryIn(categories));
        }
    }
}
