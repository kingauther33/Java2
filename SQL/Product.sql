CREATE database if not exists Products;

USE Products;

drop table if exists Product;
create table product (
    id int primary key auto_increment,
    name varchar(50),
    price float
);

insert into product values (null, 'Iphone 5', 10.50),
                           (null, 'Iphone 6', 12.50),
                           (null, 'Iphone 7', 20.50);
