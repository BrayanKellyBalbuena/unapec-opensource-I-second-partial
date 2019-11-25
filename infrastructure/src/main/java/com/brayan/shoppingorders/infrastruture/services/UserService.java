package com.brayan.shoppingorders.infrastruture.services;

import com.brayan.shoppingorders.core.interfaces.Service;
import com.brayan.shoppingorders.core.models.User;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public interface UserService extends Service<User, Long> {
    Optional<User> login(User user) throws NoSuchAlgorithmException;
}
