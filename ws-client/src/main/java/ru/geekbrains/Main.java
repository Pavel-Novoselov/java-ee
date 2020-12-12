package ru.geekbrains;

import ru.geekbrains.service.*;

import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://127.0.0.1:8080/lesson-6/ProductService?wsdl");
        ProductServiceImplService productServiceWs = new ProductServiceImplService(url);
        ProductServiceWs port = productServiceWs.getProductServicePort();
        System.out.println(port.findAll());
    }
}
