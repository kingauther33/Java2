CREATE DATABASE if not exists ebookshop;

USE ebookshop;

drop table if exists books;
create table books(
    id int,
    title varchar(50),
    author varchar(50),
    price float,
    qty int,
    primary key (id)
);

insert into books values (1001, 'Java for Dummies', 'Dang Kim Thi', 11.11, 11),
                         (1002, 'More Java for Dummies', 'Codelean VN', 22.22, 22);





