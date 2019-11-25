package com.brayan.shoppingorders.infrastruture.repositories.impl.database;

import com.brayan.shoppingorders.core.models.User;
import com.brayan.shoppingorders.infrastruture.enums.ModelState;
import com.brayan.shoppingorders.infrastruture.repositories.UserRepository;
import com.brayan.shoppingorders.infrastruture.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;

@Repository
@Transactional
public class UserRepositoryDatabaseImpl extends RepositoryDatabaseImpl<User, Long> implements UserRepository {
    public UserRepositoryDatabaseImpl(HibernateUtil hibernateUtil) {
        super(hibernateUtil, User.class);
    }

    @Override
    public User login(User user) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(genericType);
        Root<User> root = query.from(User.class);
        Path<String> email = root.get("email");
        Path<String> password = root.get("password");
        Path<Boolean> state = root.get("state");

        Predicate emailPredicate = criteriaBuilder.equal(email, user.getEmail());
        Predicate passwordPredicate = criteriaBuilder.equal(password, user.getPassword());
        Predicate statusPredicate = criteriaBuilder.equal(state, ModelState.ACTIVE.getIntValue());
        Predicate predicate = criteriaBuilder.and(emailPredicate, passwordPredicate, statusPredicate);

        query.select(root).where(predicate);

        Query<User> q = session.createQuery(query);
        User result = q.uniqueResult();

        session.close();

        return result;
    }
}
