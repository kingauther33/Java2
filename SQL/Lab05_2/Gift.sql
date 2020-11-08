drop database if exists giftstore;

create database if not exists giftstore;

use giftstore;

drop table if exists giftItem;

create table giftItem(
    ID int primary key,
    Name varchar(60),
    price double,
    qty int
);

insert into giftItem values (1, 'Gift 1', 11, 5),
                            (2, 'Gift 2', 12, 6),
                            (3, 'Gift 3', 13, 7),
                            (4, 'Gift 4', 14, 8),
                            (5, 'Gift 5', 15, 9),
                            (6, 'Gift 6', 16, 10);