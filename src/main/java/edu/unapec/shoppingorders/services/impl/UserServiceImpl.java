package edu.unapec.shoppingorders.services.impl;

import edu.unapec.shoppingorders.models.User;
import edu.unapec.shoppingorders.repositories.UserRepository;
import edu.unapec.shoppingorders.services.UserService;
import edu.unapec.shoppingorders.utils.HashUtil;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class UserServiceImpl extends BaseService<User, Long> implements UserService {
    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    public Optional<User> login(User user) throws NoSuchAlgorithmException {
        user.setPassword(HashUtil.stringToSha256(user.getPassword()));
        Optional<User> result = Optional.ofNullable(((UserRepository) repository).login(user));
        return result;
    }
}
