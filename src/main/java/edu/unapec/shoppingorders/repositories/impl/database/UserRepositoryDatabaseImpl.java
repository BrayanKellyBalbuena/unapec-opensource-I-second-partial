package edu.unapec.shoppingorders.repositories.impl.database;

import edu.unapec.shoppingorders.models.User;
import edu.unapec.shoppingorders.repositories.UserRepository;
import edu.unapec.shoppingorders.utils.HibernateUtil;
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

        Predicate emailPredicate = criteriaBuilder.equal(email, user.getEmail());
        Predicate passwordPredicate = criteriaBuilder.equal(password, user.getPassword());
        Predicate emailAndPasswordPredicate = criteriaBuilder.and(emailPredicate, passwordPredicate);

        query.select(root).where(emailAndPasswordPredicate);

        Query<User> q = session.createQuery(query);
        User result = q.uniqueResult();

        session.close();

        return result;
    }
}
