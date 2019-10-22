package edu.unapec.shoppingorders.repositories.impl.database;

import edu.unapec.shoppingorders.models.Location;
import edu.unapec.shoppingorders.repositories.LocationRepository;
import edu.unapec.shoppingorders.utils.HibernateUtil;

public class LocationRepositoryDatabaseImpl extends RepositoryDatabaseImpl<Location, Long> implements LocationRepository {
    public LocationRepositoryDatabaseImpl(HibernateUtil hibernateUtil) {
        super(hibernateUtil, Location.class);
    }
}
