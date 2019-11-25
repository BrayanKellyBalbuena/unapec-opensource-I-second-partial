package com.brayan.shoppingorders.infrastruture.services.impl;

import com.brayan.shoppingorders.core.interfaces.Repository;
import com.brayan.shoppingorders.core.models.Location;
import com.brayan.shoppingorders.infrastruture.services.LocationService;

public class LocationServiceImpl extends BaseService<Location, Long> implements LocationService {
    public LocationServiceImpl(Repository<Location, Long> repository) {
        super(repository);
    }
}
