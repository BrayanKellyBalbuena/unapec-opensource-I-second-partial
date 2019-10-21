package edu.unapec.shoppingorders.services;

import java.io.Serializable;
import java.util.List;

public interface Service<T, ID extends Serializable> {
    ID save(T entity);
    void delete(ID id);
    void update(T entity);
    T findById(ID id);
    List<T> findAll();
}
