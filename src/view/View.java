package view;

import controller.UserController;

public class View {

    public static UserController userController = new UserController();

    public static void ui(){
        userController.userList().forEach(System.out::println);
//        do {
//            String ch = new Scanner(System.in).nextLine();
//            switch (ch){
//                case 'x' -> {
//                    System.exit(0);
//                    System.out.println("Good bye See you again!!");
//                }
//                default -> System.out.println(STR."\{"+".repeat(60)}# Please input base on option above\n\{"+".repeat(60)}");
//
//            }
//        }while (true);
    }
}
