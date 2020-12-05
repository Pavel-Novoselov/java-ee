package ru.geekbrains.feign;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import ru.geekbrains.service.ProductRepr;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        FeignClient feignClient = Feign.builder()
                                         .client(new OkHttpClient())
                                         .encoder(new GsonEncoder())
                                         .decoder(new GsonDecoder())
                                         .target(FeignClient.class, "http://localhost:8080/lesson-6/api/v1/products");


        ProductRepr productRepr = feignClient.findById(14);
        System.out.println(productRepr.getDescription());
        System.out.println("***********************");
        List<ProductRepr> list = feignClient.findAll();
        list.forEach(p -> {
            System.out.println(p.getDescription());
        });
    }

}
