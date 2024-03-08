package repository;

import model.User;
import propertyloader.ConnectDb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public static List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try(Connection connection = ConnectDb.connectToDb()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                User user = User.builder()
                        .userId(resultSet.getInt("user_id"))
                        .userUuid(resultSet.getString("user_uuid"))
                        .userName(resultSet.getString("user_name"))
                        .userEmail(resultSet.getString("user_email"))
                        .userPassword(resultSet.getString("user_password"))
                        .isDeleted(resultSet.getBoolean("is_deleted"))
                        .isVerified(resultSet.getBoolean("is_verified"))
                        .build();
                users.add(user);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return users;
    }

    public static void addUser(){

    }

}
