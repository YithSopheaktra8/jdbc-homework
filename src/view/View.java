package view;

import controller.UserController;
import model.User;
import utils.PrintAsTable;

import java.util.List;

public class View {

    public static UserController userController = new UserController();
    public static User user = new User();

    public static void ui() {
        System.out.println("*".repeat(50));
        System.out.println("Welcome to User Management System");
        System.out.println("1. Add User");
        System.out.println("2. View All User");
        System.out.println("3. Search User");
        System.out.println("4. Update User");
        System.out.println("5. Set verify to User");
        System.out.println("6. Delete User");
        System.out.println("7. Exit");
        System.out.println("*".repeat(50));
        System.out.println("Please select an option: ");
        int option = 0;
        do {
            List<User> userList = userController.userList();
            option = new java.util.Scanner(System.in).nextInt();
            switch (option) {
                case 1 -> {
                    userController.addUser(user);
                    System.out.println("Do you want to add another user? (yes/no)");
                    String choice = new java.util.Scanner(System.in).nextLine();
                    if (choice.equalsIgnoreCase("yes")) {
                        userController.addUser(user);
                    } else {
                        ui();
                    }
                }
                case 2 -> {
                    PrintAsTable.printAsTable(userList);
                    ui();
                }
                case 3 -> {
                    User user = userController.searchUser();
                    PrintAsTable.printAsTableOneUser(user);
                    ui();
                }
                case 4 -> {
                    userController.updateUser(userList);
                    ui();
                }
                case 5 -> {
                    userController.setUserVerified(userList);
                    ui();
                }
                case 6 -> {
                    userController.deleteUser(userList);
                    ui();
                }
                case 7 -> {
                    System.exit(0);
                    System.out.println("Good bye See you again!!");
                }
                default -> System.out.println("# Please input base on option above");
            }
        } while (option != 7);
    }
}