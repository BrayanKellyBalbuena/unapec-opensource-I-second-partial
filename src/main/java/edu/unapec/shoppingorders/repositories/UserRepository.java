package edu.unapec.shoppingorders.repositories;

import edu.unapec.shoppingorders.models.User;

public interface UserRepository extends Repository<User, Long> {
    User login(User user);
}
