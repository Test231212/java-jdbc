import dao.BankDAO;
import db.DBConnection;
import model.Account;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;


public class BankApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // http://bank.com/account GET
        // http://bank.com/account/10 GET
        // http://bank.com/account POST 계좌를 만든다 *바디 데이터 필요
        // http://bank.com/account/1 DELETE 1번 계좌 삭제
        // http://bank.com/account/1 PUT 1번 계좌 수정 *바디 데이터 필요
        // get delete 요청은 http에 바디가 없다.

        // /account
        // /account/1
        // /account
        // /account/1
        // /account/1
        System.out.println("http 메서드를 입력하세요");
        String method = sc.nextLine();

        System.out.println("식별자를 입력하세요");
        String action = sc.nextLine();

        String body = "";

        BankDAO bankDAO = new BankDAO();
        if (method.equals("GET")) {
            if (action.equals("/account")) {
                List<Account> accountList = bankDAO.selectAll();
                System.out.println(accountList);
            } else if (action.equals("/account/1")) {
                Account account = bankDAO.selectByNumber(1);
                System.out.println(account);
            }
        } else if (method.equals("POST")) {
            System.out.println("body 데이터를 입력하세요.");
            body = sc.nextLine();

            // password=1234&balance=1000

            String[] st1 = body.split("&");
            String password = st1[0].split("=")[1];
            int balance = Integer.parseInt(st1[1].split("=")[1]);

            if (action.equals("/account")) {
                bankDAO.insert(password, balance);
            }

        } else if (method.equals("PUT")) {
            System.out.println("body 데이터를 입력하세요.");
            body = sc.nextLine();

            // body 데이터 파싱
            String[] st1 = body.split("&");
            String password = st1[0].split("=")[1];
            int balance = Integer.parseInt(st1[1].split("=")[1]);

            if (action.startsWith("/account/")) {
                // "/account/1" 형태의 식별자에서 숫자 부분 추출
                String[] parts = action.split("/");
                int accountNumber = Integer.parseInt(parts[2]);

                // 해당 계좌가 존재하는지 확인
                Account account = bankDAO.selectByNumber(accountNumber);

                if (account != null) {
                    // 계좌 업데이트
                    int result = bankDAO.updateByNumber(balance, accountNumber);

                    if (result == 1) {
                        System.out.println("계좌 업데이트 성공");
                    } else {
                        System.out.println("계좌 업데이트 실패");
                    }
                } else {
                    System.out.println("해당 계좌가 존재하지 않습니다.");
                }
            }
        } else if (method.equals("DELETE")) {
            if (action.startsWith("/account/")) {
                // "/account/1" 형태의 식별자에서 숫자 부분 추출
                String[] parts = action.split("/");
                int accountNumber = Integer.parseInt(parts[2]);

                // 해당 계좌가 존재하는지 확인
                Account account = bankDAO.selectByNumber(accountNumber);

                if (account != null) {
                    // 계좌 삭제
                    int result = bankDAO.deleteByNumber(accountNumber);

                    if (result == 1) {
                        System.out.println("계좌 삭제 성공");
                    } else {
                        System.out.println("계좌 삭제 실패");
                    }
                } else {
                    System.out.println("해당 계좌가 존재하지 않습니다.");
                }
            }
        }

    }
}
