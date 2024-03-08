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
    public static List<User> getAllUserToList(){
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

    public static List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        String sql = "";
        System.out.println("1. Get all users");
        System.out.println("2. Get all deleted users");
        System.out.println("3. Get all verify users");
        String choice = Validate.validateInputString("Enter your choice: ", "Invalid choice", "^[1-3]$", new Scanner(System.in));
        switch (choice){
            case "1" -> {
                sql = "SELECT * FROM users WHERE is_deleted = false";
            }
            case "2" -> {
                sql = "SELECT * FROM users WHERE is_deleted = true";
            }
            case "3" ->{
                sql = "SELECT * FROM users WHERE is_verified = true AND is_deleted = false";
            }
        }
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
    public static User searchUser(){
        User user = new User();
        String sql = "SELECT * FROM users WHERE user_uuid = ?";
        try(Connection connection = ConnectDb.connectToDb()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            String userUuid = Validate.validateInputString("Enter user uuid: ", "Invalid uuid", "^[a-zA-Z0-9-]+$", new Scanner(System.in));
            ps.setString(1, userUuid);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                user = User.builder()
                        .userId(resultSet.getInt("user_id"))
                        .userUuid(resultSet.getString("user_uuid"))
                        .userName(resultSet.getString("user_name"))
                        .userEmail(resultSet.getString("user_email"))
                        .userPassword(resultSet.getString("user_password"))
                        .isDeleted(resultSet.getBoolean("is_deleted"))
                        .isVerified(resultSet.getBoolean("is_verified"))
                        .build();
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return user;
    }

    public static void updateUser(List<User> users){
        User user = new User();
        String sql = "";
        String choice = Validate.validateInputString("Which one do you want to update? (all/name/email/password): ", "Invalid choice", "^(all|name|email|password)$", new Scanner(System.in));
        try(Connection connection = ConnectDb.connectToDb()) {
            String userUuid = Validate.validateInputString("Enter user uuid: ", "Invalid uuid", "^[a-zA-Z0-9-]+$", new Scanner(System.in));
            user = users.stream().filter(u -> u.getUserUuid().equals(userUuid)).findFirst().orElseThrow();
            switch (choice){
                case "name" -> {
                    sql = "UPDATE users SET user_name = ? WHERE user_uuid = ?";
                    PreparedStatement ps = connection.prepareStatement(sql);
                    user.setUserName(Validate.validateInputString("Enter your name: ", "Invalid name", "^[a-zA-Z ]+$", new Scanner(System.in)));
                    ps.setString(1, user.getUserName());
                    ps.setString(2, user.getUserUuid());
                    ps.executeUpdate();

                }
                case "email" -> {
                    sql = "UPDATE users SET user_email = ? WHERE user_uuid = ?";
                    PreparedStatement ps = connection.prepareStatement(sql);
                    user.setUserEmail(Validate.validateInputString("Enter your email: ", "Invalid email", "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", new Scanner(System.in)));
                    ps.setString(1, user.getUserEmail());
                    ps.setString(2, user.getUserUuid());
                    ps.executeUpdate();

                }
                case "password" -> {
                    sql = "UPDATE users SET user_password = ? WHERE user_uuid = ?";
                    PreparedStatement ps = connection.prepareStatement(sql);
                    user.setUserPassword(Validate.validateInputString("Enter your password: ", "Password must be 8 characters with number and 1 Uppercase with special character\n example : Tra@1234", "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", new Scanner(System.in)));
                    ps.setString(1, user.getUserPassword());
                    ps.setString(2, user.getUserUuid());
                    ps.executeUpdate();

                }
                case "all" -> {
                    sql = "UPDATE users SET user_name = ?, user_email = ?, user_password = ? WHERE user_uuid = ?";
                    PreparedStatement ps = connection.prepareStatement(sql);
                    user.setUserName(Validate.validateInputString("Enter your name: ", "Invalid name", "^[a-zA-Z ]+$", new Scanner(System.in)));
                    user.setUserEmail(Validate.validateInputString("Enter your email: ", "Invalid email", "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", new Scanner(System.in)));
                    user.setUserPassword(Validate.validateInputString("Enter your password: ", "Password must be 8 characters with number and 1 Uppercase with special character\n example : Tra@1234", "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", new Scanner(System.in)));
                    ps.setString(1, user.getUserName());
                    ps.setString(2, user.getUserEmail());
                    ps.setString(3, user.getUserPassword());
                    ps.setString(4, user.getUserUuid());
                    ps.executeUpdate();
                }
            }
            System.out.println("User updated successfully");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void setVerifyUser(List<User> users) {
        String sql = "UPDATE users SET is_verified = true WHERE user_uuid = ?";
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
