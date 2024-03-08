package utils;

import model.User;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;

public class PrintAsTable {
    public static void printAsTable(List<User> userList) {
        Table table = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
        CellStyle cellCenter = new CellStyle(CellStyle.HorizontalAlign.center);
        table.addCell("USER UUID", cellCenter);
        table.addCell("USER NAME", cellCenter);
        table.addCell("USER EMAIL", cellCenter);
        table.addCell("USER PASSWORD", cellCenter);
        table.addCell("USER VERIFIED", cellCenter);
        userList.forEach(user -> {
            table.addCell(user.getUserUuid());
            table.addCell(user.getUserName());
            table.addCell(user.getUserEmail());
            table.addCell(user.getUserPassword());
            table.addCell(user.getIsVerified().toString());
        });

        System.out.println(table.render());
    }
    public static void printAsTableOneUser(User user) {
        Table table = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
        CellStyle cellCenter = new CellStyle(CellStyle.HorizontalAlign.center);
        if(user.getIsVerified() == null){
            System.out.println("User not found");
            return;
        }
        table.addCell("USER UUID", cellCenter);
        table.addCell("USER NAME", cellCenter);
        table.addCell("USER EMAIL", cellCenter);
        table.addCell("USER PASSWORD", cellCenter);
        table.addCell("USER VERIFIED", cellCenter);
        table.addCell(user.getUserUuid());
        table.addCell(user.getUserName());
        table.addCell(user.getUserEmail());
        table.addCell(user.getUserPassword());
        table.addCell(user.getIsVerified().toString());
        System.out.println(table.render());
    }
}
