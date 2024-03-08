package utils;

import model.User;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;

public class PrintAsTable {
    public static void printAsTable(List<User> userList) {
        Table table = new Table(6, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
        CellStyle cellCenter = new CellStyle(CellStyle.HorizontalAlign.center);
        table.addCell("USER ID",cellCenter);
        table.addCell("USER UUID", cellCenter);
        table.addCell("USER NAME", cellCenter);
        table.addCell("USER EMAIL", cellCenter);
        table.addCell("USER PASSWORD", cellCenter);
        table.addCell("USER VERIFIED", cellCenter);
        userList.forEach(user -> {
            table.addCell(user.getUserId().toString(),cellCenter);
            table.addCell(user.getUserUuid());
            table.addCell(user.getUserName());
            table.addCell(user.getUserEmail());
            table.addCell(user.getUserPassword());
            table.addCell(user.getIsVerified().toString());
        });

        System.out.println(table.render());
    }
    public static void printAsTableOneUser(User user) {
        Table table = new Table(6, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
        CellStyle cellCenter = new CellStyle(CellStyle.HorizontalAlign.center);
        if(user.getIsVerified() == null){
            System.out.println("User not found");
            return;
        }
        table.addCell("USER ID",cellCenter);
        table.addCell("USER UUID", cellCenter);
        table.addCell("USER NAME", cellCenter);
        table.addCell("USER EMAIL", cellCenter);
        table.addCell("USER PASSWORD", cellCenter);
        table.addCell("USER VERIFIED", cellCenter);
        table.addCell(user.getUserId().toString(),cellCenter);
        table.addCell(user.getUserUuid());
        table.addCell(user.getUserName());
        table.addCell(user.getUserEmail());
        table.addCell(user.getUserPassword());
        table.addCell(user.getIsVerified().toString());
        System.out.println(table.render());
    }

    public static void menu(){
        String menu = """
                Welcome to User Management System
                        1. Add User
                        2. View All User
                        3. Search User
                        4. Update User
                        5. Set verify to User
                        6. Delete User
                        7. Exit
                """;
        Table table = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.SURROUND_HEADER_AND_COLUMNS);
        CellStyle cellCenter = new CellStyle(CellStyle.HorizontalAlign.center);
        table.setColumnWidth(0,50,50);
        table.addCell("Welcome to User Management System",cellCenter);
        table.addCell("1. Add User",cellCenter);
        table.addCell("2. View All User",cellCenter);
        table.addCell("3. Search User",cellCenter);
        table.addCell("4. Update User",cellCenter);
        table.addCell("5. Set verify to User",cellCenter);
        table.addCell("6. Delete User",cellCenter);
        table.addCell("7. Exit",cellCenter);
        System.out.println(table.render());
    }
}
