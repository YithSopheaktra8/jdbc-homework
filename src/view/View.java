package view;

import controller.UserController;
import model.User;

import java.util.List;

public class View {

    public static UserController userController = new UserController();
    public static User user = new User();
    public static List<User> userList = userController.userList();

    public static void ui() {
        System.out.println("Welcome to User Management System");
        System.out.println("1. Add User");
        System.out.println("2. View User");
        System.out.println("3. Delete User");
        System.out.println("4. Exit");
        System.out.println("Please select an option: ");
        int option = 0;
        do {
            option = new java.util.Scanner(System.in).nextInt();
            switch (option) {
                case 1 -> {
                    userController.addUser(user);
                    System.out.println("Do you want to add another user? (yes/no)");
                    String choice = new java.util.Scanner(System.in).nextLine();
                    if (choice.equalsIgnoreCase("no")) {
                        ui();
                    }
                }
                case 2 -> {
                    userList = userController.userList();
                    userList.forEach(System.out::println);
                    ui();
                }
                case 3 -> {
                    userController.deleteUser(userList);
                    ui();
                }
                case 4 -> {
                    System.exit(0);
                    System.out.println("Good bye See you again!!");
                }
                default -> System.out.println("# Please input base on option above");
            }
        } while (option != 4);
//
    }
}