package ru.geekbrains.springmarket;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import ru.geekbrains.springmarket.entities.Category;
import ru.geekbrains.springmarket.entities.Product;
import ru.geekbrains.springmarket.entities.dto.ProductDto;
import ru.geekbrains.springmarket.exceptions.ResourceNotFoundException;
import ru.geekbrains.springmarket.repositories.ProductRepository;
import ru.geekbrains.springmarket.services.ProductService;
import ru.geekbrains.springmarket.utils.MyPage;
import ru.geekbrains.springmarket.utils.ProductFilter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootTest(classes = ProductService.class)
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void findAllProducts() {
        Mockito.doReturn(generateProducts())
                .when(productRepository)
                .findAll(new ProductFilter(Map.of()).getSpec(), PageRequest.of(0, 5));

        MyPage<ProductDto> productPage = productService.findAll(new ProductFilter(Map.of()).getSpec(), 0, 5);
        Assertions.assertNotNull(productPage);
        Assertions.assertNotNull(productPage.getContent());
        Assertions.assertEquals(2, productPage.getContent().size());
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    public void findProductById() {
        Mockito.doReturn(generateProduct())
                .when(productRepository)
                .findById(1L);

        Product apple = productService.findById(1L).get();
        Assertions.assertNotNull(apple);
        Assertions.assertEquals("Яблоко", apple.getTitle());
        Assertions.assertEquals("Продукты", apple.getCategory().getTitle());
        Mockito.verify(productRepository, Mockito.times(1)).findById(ArgumentMatchers.anyLong());
    }

    private Page<Product> generateProducts() {
        Category category = new Category();
        category.setId(1L);
        category.setTitle("Продукты");

        Product apple = new Product();
        apple.setId(1L);
        apple.setPrice(10f);
        apple.setTitle("Яблоко");
        apple.setCategory(category);

        Product meat = new Product();
        meat.setId(2L);
        meat.setPrice(20f);
        meat.setTitle("Мясо");
        meat.setCategory(category);

        List<Product> content = Arrays.asList(apple, meat);
        return new PageImpl<>(content, PageRequest.of(0, 5), content.size());
    }

    private Optional<Product> generateProduct() {
        Category category = new Category();
        category.setId(1L);
        category.setTitle("Продукты");

        Product apple = new Product();
        apple.setId(1L);
        apple.setPrice(10f);
        apple.setTitle("Яблоко");
        apple.setCategory(category);
        return Optional.of(apple);
    }

}
