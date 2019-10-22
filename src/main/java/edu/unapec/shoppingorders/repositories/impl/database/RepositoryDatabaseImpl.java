package edu.unapec.shoppingorders.repositories.impl.database;

import edu.unapec.shoppingorders.models.BaseModel;
import edu.unapec.shoppingorders.repositories.Repository;
import edu.unapec.shoppingorders.utils.HibernateUtil;
import lombok.Data;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RepositoryDatabaseImpl<T extends BaseModel, ID extends Serializable> implements Repository<T, ID> {

    protected Class<T> genericType;
    protected Logger LOG;
    protected SessionFactory sessionFactory;

    public RepositoryDatabaseImpl(HibernateUtil hibernateUtil, Class<T> genericType) {
        this.genericType = genericType;
        this.sessionFactory = hibernateUtil.getSessionFactory();
        LOG = Logger.getLogger(genericType.getName());
    }

    @Override
    public ID add(T entity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        ID id = (ID) session.save(entity);

        session.getTransaction().commit();
        session.close();
        LOG.log(Level.INFO, "Guardado");

        return id;
    }

    @Override
    public void delete(ID id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        T model = findById(id);
        if (model != null) {
            model.setState(false);
            session.update(model);
            session.getTransaction().commit();
            session.close();
            LOG.log(Level.INFO, "Eliminado");
        }
        ;

    }

    @Override
    public void update(T model) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        if (findById((ID) model.getId()) != null) {
            session.update(model);
            session.getTransaction().commit();
            session.close();
            LOG.log(Level.INFO, "Actualizado");
        }
    }

    @Override
    public T findById(ID id) {
        Session session = sessionFactory.openSession();
        T medico = session.get(genericType, (Serializable) id);
        session.close();
        LOG.log(Level.INFO, "Encontrado");
        return medico;
    }

    @Override
    public List<T> getAll() {

        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(genericType);
        Root<T> root = query.from(genericType);

        query.select(root).where(builder.equal(root.get("state"), 1L));
        Query<T> q = session.createQuery(query);
        List<T> models = q.getResultList();

        session.close();
        return models;
    }
}
