package model;

import repository.UserRepository;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public List<User> getAllUser() {
        return UserRepository.getAllUsers();
    }

    @Override
    public void addUser(User user) {
        UserRepository.addUser(user);
    }

    @Override
    public void deleteUser(List<User> users) {
        UserRepository.deleteUser(users);
    }
}
