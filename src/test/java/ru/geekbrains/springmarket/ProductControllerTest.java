package ru.geekbrains.springmarket;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import ru.geekbrains.springmarket.entities.Product;
import ru.geekbrains.springmarket.entities.dto.ProductDto;
import ru.geekbrains.springmarket.utils.MyPage;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("h2")
public class ProductControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @SuppressWarnings("unchecked")
    @Test
    public void getProductsPage() {
        MyPage<ProductDto> products = restTemplate.getForObject("/api/v1/products", MyPage.class);
        Assertions.assertNotNull(products);
        Assertions.assertNotNull(products.getContent());
        Assertions.assertEquals(4, products.getTotalPages());
        Assertions.assertEquals(20, products.getTotalElements());
    }

    @Test
    public void getProductById() {
        Product product = restTemplate.getForObject("/api/v1/products/{id}", Product.class, 2);
        Assertions.assertNotNull(product);
        Assertions.assertEquals(2, product.getId());
    }

    @Test
    public void getProductByNonExistentId() {
        ResponseEntity<?> entity = restTemplate.getForEntity("/api/v1/products/{id}", Product.class, 999);
        Assertions.assertEquals(404, entity.getStatusCodeValue());
    }
}
