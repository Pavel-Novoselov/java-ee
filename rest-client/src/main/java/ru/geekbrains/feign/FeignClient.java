package ru.geekbrains.feign;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import ru.geekbrains.service.ProductRepr;

import java.util.List;

public interface FeignClient {
    @RequestLine("GET /{id}")
    ProductRepr findById(@Param("id") long id);

    @RequestLine("GET /all")
    List<ProductRepr> findAll();

    @RequestLine("POST")
    @Headers("Content-Type: application/json")
    void create(ProductRepr productRepr);
}
