import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BankApp {
    public static void main(String[] args) {
        Connection conn = DBConnection.getInstance();

        try {
            String update = "update account_tb set balance = balance + ? where number = ?";
            PreparedStatement pstmt = conn.prepareStatement(update);
            pstmt.setInt(1, 5000);
            pstmt.setInt(2, 2);
            int num = pstmt.executeUpdate();
            System.out.println(num);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
