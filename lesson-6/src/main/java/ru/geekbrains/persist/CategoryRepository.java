package ru.geekbrains.persist;

import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import java.util.List;

@Local
public interface CategoryRepository {
    public Category findById(long id);

    public List<Category> findAll() ;

    @TransactionAttribute
    public void insert(Category category);

    @TransactionAttribute
    public void update(Category category);

    @TransactionAttribute
    public void delete(long id);
}
