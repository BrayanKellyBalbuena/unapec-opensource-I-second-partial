package edu.unapec.shoppingorders.services.impl;

import edu.unapec.shoppingorders.models.User;
import edu.unapec.shoppingorders.repositories.UserRepository;
import edu.unapec.shoppingorders.services.UserService;
import edu.unapec.shoppingorders.utils.HashUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class UserServiceImpl extends BaseService<User, Long> implements UserService {
    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    public Optional<User> login(User user) throws NoSuchAlgorithmException {
        user.setPassword(HashUtil.stringToSha256(user.getPassword()));
        Optional<User> result = Optional.ofNullable(((UserRepository) repository).login(user));
        return result;
    }

    @Override
    public Long save(User model) throws IOException, NoSuchAlgorithmException {
        model.setPassword(HashUtil.stringToSha256(model.getPassword()));
        return repository.add(model);
    }

    @Override
    public void update(User entity) throws IOException, NoSuchAlgorithmException {
        entity.setPassword(HashUtil.stringToSha256(entity.getPassword()));
        super.update(entity);
    }
}
