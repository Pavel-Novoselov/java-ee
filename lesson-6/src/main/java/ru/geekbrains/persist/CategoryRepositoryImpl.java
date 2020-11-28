package ru.geekbrains.persist;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class CategoryRepositoryImpl implements CategoryRepository {
    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Override
    public Category findById(long id) {
        return em.find(Category.class, id);
    }

    @Override
    public List<Category> findAll() {
        return em.createQuery("select t from Category t", Category.class)
                 .getResultList();
    }

    @Override
    @TransactionAttribute
    public void insert(Category category) {
        em.persist(category);
    }

    @Override
    @TransactionAttribute
    public void update(Category category) {
        em.merge(category);
    }

    @Override
    @TransactionAttribute
    public void delete(long id) {
        Category category = em.find(Category.class, id);
        if (category != null) {
            em.remove(category);
        }
    }
}
