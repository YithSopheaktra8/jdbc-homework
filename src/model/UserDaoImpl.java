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
    public User searchUser() {
        return UserRepository.searchUser();
    }

    @Override
    public void updateUser(List<User> users) {
        UserRepository.updateUser(users);
    }

    @Override
    public void deleteUser(List<User> users) {
        UserRepository.deleteUser(users);
    }
}
