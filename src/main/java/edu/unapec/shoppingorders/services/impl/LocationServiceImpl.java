package edu.unapec.shoppingorders.services.impl;

import edu.unapec.shoppingorders.models.Location;
import edu.unapec.shoppingorders.repositories.Repository;
import edu.unapec.shoppingorders.services.LocationService;

public class LocationServiceImpl extends BaseService<Location, Long> implements LocationService {
    public LocationServiceImpl(Repository<Location, Long> repository) {
        super(repository);
    }
}
