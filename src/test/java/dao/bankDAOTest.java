package dao;

import db.DBConnection;
import model.Account;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class bankDAOTest {

    @Test
    public void selectAll_test(){
        // given
        // when
        BankDAO dao = new BankDAO();
        List<Account> accountList = dao.selectAll();

        System.out.println(accountList.size());
        System.out.println(accountList);
    }

    @Test
    public void selectByNumber_test(){
        int number = 5;

        BankDAO dao = new BankDAO();
        Account account = dao.selectByNumber(number);

        if(account == null) {
            System.out.println(number + "로 출력된 값이 없습니다.");
        }else {
            System.out.println(account);
//            System.out.println(account.getNumber());
//            System.out.println(account.getPassword());
//            System.out.println(account.getBalance());
//            System.out.println(account.getCreateAt());
        }
    }

    @Test
    public void deleteByNumber_test() {
        // given
        int number = 10;

        // when
        BankDAO dao = new BankDAO();
        int result = dao.deleteByNumber(number);

        // then
        if (result == 1) {
            System.out.println("삭제 성공");
        } else if (result == 0) {
            System.out.println("번호를 찾을 수 없습니다.");
        } else {
            System.out.println("삭제 실패");
        }
    }

}
