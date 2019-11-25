package com.brayan.shoppingorders.core.interfaces;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public interface Repository<T, ID extends Serializable> {
    ID add(T entity) throws IOException;
    void update(T entity) throws IOException;
    void delete(ID id) throws IOException;
    T findById(ID id) throws IOException;
    List<T> getAll();
}
