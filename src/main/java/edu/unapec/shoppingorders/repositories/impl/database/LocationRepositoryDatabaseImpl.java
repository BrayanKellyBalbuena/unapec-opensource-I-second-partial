package edu.unapec.shoppingorders.repositories.impl.database;

import edu.unapec.shoppingorders.models.Location;
import edu.unapec.shoppingorders.repositories.LocationRepository;
import edu.unapec.shoppingorders.utils.HibernateUtil;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class LocationRepositoryDatabaseImpl extends RepositoryDatabaseImpl<Location, Long> implements LocationRepository {
    public LocationRepositoryDatabaseImpl(HibernateUtil hibernateUtil) {
        super(hibernateUtil, Location.class);
    }
}
