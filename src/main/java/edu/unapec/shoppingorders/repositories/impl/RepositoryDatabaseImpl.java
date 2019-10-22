package edu.unapec.shoppingorders.repositories.impl;

import edu.unapec.shoppingorders.models.BaseModel;
import edu.unapec.shoppingorders.repositories.Repository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RepositoryDatabaseImpl<T extends BaseModel, ID extends Serializable> implements Repository<T, ID> {

    private Class<T> genericType;
    private Logger LOG = Logger.getLogger(genericType.getName());
    private SessionFactory sessionFactory;

    public RepositoryDatabaseImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ID add(T entity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        ID id = (ID) session.save(entity);
        
        session.getTransaction().commit();
        session.close();
        LOG.log(Level.INFO, "Medico Guardado");

        return id;
    }

    @Override
    public void delete(ID id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        T model = findById(id);
        if (model != null) {
            session.delete(model);
            session.getTransaction().commit();
            session.close();
            LOG.log(Level.INFO, "Medico Eliminado");
        };

    }

    @Override
    public void update(T entity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        if (findById((ID) entity.getId()) != null) {
            session.update(entity);
            session.getTransaction().commit();
            session.close();
            LOG.log(Level.INFO, "Actualizado");
        }
    }

    @Override
    public T findById(ID id) {
        Session session = sessionFactory.openSession();
        T medico = session.get(genericType, (Long) id);
        session.close();
        LOG.log(Level.INFO, "Encontrado");
        return medico;
    }

    @Override
    public List<T> getAll() {
        Session session = sessionFactory.openSession();

        CriteriaQuery<T> criteriaQuery = session.getCriteriaBuilder().createQuery(genericType);
        criteriaQuery.from(genericType);

        List<T> models = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return models;
    }
}
