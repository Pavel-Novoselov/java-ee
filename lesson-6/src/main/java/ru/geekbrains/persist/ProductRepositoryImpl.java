package ru.geekbrains.persist;

import ru.geekbrains.service.ProductRepr;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Override
    @TransactionAttribute
    public void insert(Product product) {
        em.persist(product);
    }

    @Override
    @TransactionAttribute
    public void update(Product product) {
        em.merge(product);
    }

    @Override
    @Transactional
    public void delete(long id) {
        Product product = em.find(Product.class, id);
        if (product != null) {
            em.remove(product);
        }
    }

    @Override
    public Product findById(long id) {
        return em.find(Product.class, id);
    }

    @Override
    public List<Product> findAll() {
        return em.createQuery("select p from Product p", Product.class)
                 .getResultList();
    }

    @Override
    public ProductRepr findProductReprById(long id) {
        return em.createQuery("select new ru.geekbrains.service.ProductRepr(p.id, p.description, p.price, c) " +
                              "from Product p "+
                              " left join p.category c " +
                              "where p.id = :id", ProductRepr.class)
                 .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<ProductRepr> findAllProductRepr() {
        return em.createQuery("select new ru.geekbrains.service.ProductRepr(p.id, p.description, p.price, c) " +
                              "from Product p " +
                              " left join p.category c ", ProductRepr.class)
                .getResultList();
    }

}