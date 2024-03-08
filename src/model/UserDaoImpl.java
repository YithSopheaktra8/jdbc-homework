package model;

import repository.UserRepository;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public List<User> getAllUser() {
        return UserRepository.getAllUsers();
    }
}
