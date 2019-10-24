package edu.unapec.shoppingorders.services;

import edu.unapec.shoppingorders.models.User;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public interface UserService extends Service<User, Long> {
    Optional<User> login(User user) throws NoSuchAlgorithmException;
}
