package dao;


import db.DBConnection;
import model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO - DATA Access Object
 * SRP - 단일책임의 원칙
 */
public class BankDAO {

    public int deleteByNumber(int number){
        Connection conn = DBConnection.getInstance();
        try {
            String sql = "delete from account_tb where number = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, number);
            int num = pstmt.executeUpdate();
            return num;

        } catch (Exception e) {
            e.printStackTrace();
        }
    return -1;
    }

    public int insert(String password, int balance){
        Connection conn = DBConnection.getInstance();
        try {
            String sql = "insert into account_tb(password, balance, created_at) values(?, ?, now())";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, password);
            pstmt.setInt(2, balance);
            int num = pstmt.executeUpdate();
            return num;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int updateByNumber(int balance, int number){
        Connection conn = DBConnection.getInstance();
        try {
            String sql = "update account_tb set balance = ? where number = ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, balance);
            pstmt.setInt(2, number);
            int num = pstmt.executeUpdate();
            return num;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;

    }

    public Account selectByNumber(int number){
        Connection conn = DBConnection.getInstance();
        try {
            String sql = "select * from account_tb where number = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, number);

            ResultSet rs = pstmt.executeQuery();


            if(rs.next()){
                Account account = new Account(
                        rs.getInt("number"),
                        rs.getString("password"),
                        rs.getInt("balance"),
                        rs.getTimestamp("created_at")
                );
                return account;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



            // System.out.println();

            // System.out.println(rs.getInt("number"));
            // System.out.println(rs.getString("password"));
            // System.out.println(rs.getInt("balance"));
            // System.out.println(rs.getTimestamp("created_at"));



    public List<Account> selectAll(){
            Connection conn = DBConnection.getInstance();
            try {
                String sql = "select * from account_tb order by number desc";
                PreparedStatement pstmt = conn.prepareStatement(sql);

                ResultSet rs = pstmt.executeQuery();

                List<Account> accountList = new ArrayList<>();
                while(rs.next()){

                    Account account = new Account(
                            rs.getInt("number"),
                            rs.getString("password"),
                            rs.getInt("balance"),
                            rs.getTimestamp("created_at")
                    );
                    accountList.add(account);
                }
                return accountList;
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;

    }

}
