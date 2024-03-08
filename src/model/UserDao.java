package model;

import java.util.List;

public interface UserDao {
    List<User> getAllUser();
    void addUser(User user);
    User searchUser();
    void updateUser(List<User> users);

    void deleteUser(List<User> users);
}
