package model;

import java.util.List;

public interface UserDao {
    List<User> getAllUser();
    void addUser(User user);

    void deleteUser(List<User> users);
}
