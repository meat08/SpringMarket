package ru.geekbrains.springmarket.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.geekbrains.springmarket.services.ProductService;
import ru.geekbrains.springmarket.soap.products.GetProductResponse;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://localhost/market/products";

    private final ProductService productService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductRequest")
    @ResponsePayload
    public GetProductResponse getCountry() {
        GetProductResponse response = new GetProductResponse();
        response.getProduct().addAll(productService.findAllSOAP());
        return response;
    }
}
