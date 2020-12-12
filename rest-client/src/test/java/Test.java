import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import org.junit.Assert;
import ru.geekbrains.feign.FeignClient;
import ru.geekbrains.persist.Category;
import ru.geekbrains.service.ProductRepr;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class Test extends Assert {
    FeignClient feignClient = Feign.builder()
                                   .client(new OkHttpClient())
                                   .encoder(new GsonEncoder())
                                   .decoder(new GsonDecoder())
                                   .target(FeignClient.class, "http://localhost:8080/lesson-6/api/v1/products");

    @org.junit.Test
    public void givenBookClient_shouldRunSuccessfully() throws Exception {
        List<String> productReprList = feignClient.findAll().stream()
                                                      .map(ProductRepr::getDescription)
                                                      .collect(Collectors.toList());

        assertTrue(productReprList.size() > 7);
    }

    @org.junit.Test
    public void givenBookClient_shouldFindOneBook() throws Exception {
       String description = feignClient.findById(14).getDescription();
        assertEquals(description, "21yyyyy1");
    }

    @org.junit.Test
    public void givenBookClient_shouldPostBook() throws Exception {
        ProductRepr productRepr = new ProductRepr("It is test!", BigDecimal.valueOf(99.00), 2);
        feignClient.create(productRepr);
        productRepr = feignClient.findById(16L);

        assertEquals(productRepr.getDescription(), "It is test!");
    }
}
