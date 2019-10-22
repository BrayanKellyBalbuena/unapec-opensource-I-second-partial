package edu.unapec.shoppingorders.services;

import edu.unapec.shoppingorders.models.BaseModel;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public interface Service<T extends BaseModel, ID extends Serializable> {
    ID save(T entity) throws IOException;

    void delete(ID id) throws IOException;

    void update(T entity) throws IOException;

    T findById(ID id) throws IOException;

    List<T> getAll();
}
