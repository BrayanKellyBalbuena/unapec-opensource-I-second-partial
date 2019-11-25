package com.brayan.shoppingorders.infrastruture.repositories;

import com.brayan.shoppingorders.core.interfaces.Repository;
import com.brayan.shoppingorders.core.models.User;

public interface UserRepository extends Repository<User, Long> {
    User login(User user);
}