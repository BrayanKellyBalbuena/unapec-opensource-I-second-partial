package edu.unapec.shoppingorders.services.impl;

import edu.unapec.shoppingorders.models.BaseModel;
import edu.unapec.shoppingorders.repositories.Repository;
import edu.unapec.shoppingorders.services.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@org.springframework.stereotype.Service
@Transactional
public abstract class BaseService<TModel extends BaseModel, ID extends Serializable> implements Service<TModel, ID> {
    protected Repository<TModel, ID> repository;

    public BaseService(Repository<TModel, ID> repository) {
        this.repository = repository;

    }

    @Override
    public ID save(TModel model) throws IOException {
        if (model != null) {
            model.setCreatedDate(new Date());
            model.setState(true);
            return repository.add(model);
        }

        return null;
    }

    @Override
    public void delete(ID id) throws IOException {
        repository.delete(id);
    }

    @Override
    public void update(TModel entity) throws IOException {
        entity.setModifiedDate(new Date());
        repository.update(entity);
    }

    @Override
    public TModel findById(ID id) throws IOException {
        return repository.findById(id);
    }

    @Override
    public List<TModel> getAll() {
        return repository.getAll();
    }
}
