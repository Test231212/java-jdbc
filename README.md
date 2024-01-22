SQL 파일

CREATE DATABASE cosdb;

DROP TABLE account_tb;

USE cosdb;
CREATE TABLE account_tb(
    number int primary KEY auto_increment,
    password varchar(100) not null,
    balance int not null,
    created_at timestamp not null
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

SELECT * FROM account_tb;
