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

}
