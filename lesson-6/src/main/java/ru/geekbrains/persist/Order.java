package ru.geekbrains.persist;

import org.hibernate.annotations.Cascade;
import ru.geekbrains.service.ProductRepr;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime crateDateTime;

    private BigDecimal sum;

    private String address;

    @Transient
    private List<ProductRepr> productList;

    @OneToMany(mappedBy = "order")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<OrdersProducts> ordersProductsList;

    public Order() {
    }

    public Order(List<ProductRepr> productList){
        this.ordersProductsList = new ArrayList<>();
        for (ProductRepr p: productList
        ) {
            OrdersProducts ordersProducts = new OrdersProducts();
            ordersProducts.setOrder(this);
            ordersProducts.setProduct(new Product(p.getId(), p.getDescription(), p.getPrice(), null));
            ordersProducts.setCount(1);
            ordersProductsList.add(ordersProducts);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public LocalDateTime getCrateDateTime() {
        return crateDateTime;
    }

    public void setCrateDateTime(final LocalDateTime crateDateTime) {
        this.crateDateTime = crateDateTime;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(final BigDecimal sum) {
        this.sum = sum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public List<ProductRepr> getProductList() {
        return productList;
    }

    public void setProductList(final List<ProductRepr> productList) {
        this.productList = productList;
    }

    public List<OrdersProducts> getOrdersProductsList() {
        return ordersProductsList;
    }

    public void setOrdersProductsList(final List<OrdersProducts> ordersProductsList) {
        this.ordersProductsList = ordersProductsList;
    }
}
