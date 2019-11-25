package com.brayan.shoppingorders.infrastruture.repositories.impl.database;


import com.brayan.shoppingorders.core.models.Location;
import com.brayan.shoppingorders.infrastruture.repositories.LocationRepository;
import com.brayan.shoppingorders.infrastruture.utils.HibernateUtil;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class LocationRepositoryDatabaseImpl extends RepositoryDatabaseImpl<Location, Long>
        implements LocationRepository {
    public LocationRepositoryDatabaseImpl(HibernateUtil hibernateUtil) {
        super(hibernateUtil, Location.class);
    }
}
