package controller;

import model.User;
import model.UserDao;
import model.UserDaoImpl;

import java.util.List;

public class UserController {
    public static final UserDao userDao = new UserDaoImpl();
    public  List<User> userList(){
        return userDao.getAllUser();
    }
    public void addUser(User user){
        userDao.addUser(user);
    }

    public User searchUser(){
        return userDao.searchUser();
    }
    public void updateUser(List<User> users){
        userDao.updateUser(users);
    }
    public void deleteUser(List<User> users){
        userDao.deleteUser(users);
    }

}
