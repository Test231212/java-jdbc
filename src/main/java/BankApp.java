import dao.BankDAO;
import db.DBConnection;

import java.sql.Connection;
import java.util.Scanner;


public class BankApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("삭제할 계좌번호를 입력해주세요: ");
        int number = sc.nextInt();

        BankDAO dao = new BankDAO();

        int result = dao.deleteByNumber(number);

        if(result == 1){
            System.out.println("삭제 성공했습니다.");
        }else{
            System.out.println("삭제 실패했습니다.");
        }
    }
}
