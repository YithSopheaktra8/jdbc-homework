package repository;

import model.User;
import propertyloader.ConnectDb;
import utils.Validate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class UserRepository {

    public static List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE is_deleted = false";
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

    public static void addUser(User user){
        Scanner scanner = new Scanner(System.in);
        String sql = "INSERT INTO users (user_uuid, user_name, user_email, user_password,is_verified) VALUES (?,?,?,?,?)";
        try(Connection connection = ConnectDb.connectToDb()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            user = User.builder()
                    .userUuid(UUID.randomUUID().toString())
                    .userName(Validate.validateInputString("Enter your name: ", "Invalid name", "^[a-zA-Z ]+$", scanner))
                    .userEmail(Validate.validateInputString("Enter your email: ", "Invalid email", "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", scanner))
                    .userPassword(Validate.validateInputString("Enter your password: ", "Password must be 8 characters with number and 1 Uppercase with special character\n example : Tra@1234", "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", scanner))
                    .isVerified(false)
                    .build();
            ps.setString(1, user.getUserUuid());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getUserEmail());
            ps.setString(4, user.getUserPassword());
            ps.setBoolean(5, user.getIsVerified());
            ps.executeUpdate();
            System.out.println("User added successfully");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void deleteUser(List<User> users) {
        String sql = "UPDATE users SET is_deleted = true WHERE user_uuid = ?";
        try (Connection connection = ConnectDb.connectToDb()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            String userUuid = Validate.validateInputString("Enter user uuid: ", "Invalid uuid", "^[a-zA-Z0-9-]+$", new Scanner(System.in));
            User user = users.stream().filter(u -> u.getUserUuid().equals(userUuid)).findFirst().orElseThrow();
            ps.setString(1, user.getUserUuid());
            ps.executeUpdate();
            System.out.println("User verification status updated successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
