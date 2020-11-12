drop database if exists ebookstore;

CREATE database if not exists eBookStore;

USE eBookStore;

drop table if exists category;
create table category(
    CatID int primary key not null,
    Name nvarchar(50)
);

drop table if exists books;
create table books(
    BookId int primary key auto_increment,
    CatID int,
    Name nvarchar(100),
    Price float,
    status tinyint check ( status regexp '^[1-3]$'),  /*dang ban 1, sap ve 2, da het hang 3*/
    year int,
    createdDate datetime default current_timestamp,
    updatedDate datetime default current_timestamp on update current_timestamp,
    qty int,
    constraint fk1 foreign key (CatID) references category(CatID)
);

drop table if exists customers;
create table customers(
    CustomerID int primary key auto_increment,
    Name nvarchar(50),
    Address nvarchar(50),
    Gender tinyint(1) check ( Gender regexp '^[0-1]$'), # 1 : male, 0 : female
    DOB date,
    level tinyint(1) check ( level regexp '^[1-3]$'), /* 1 vjp1, 2 vjp2 , 3 richvjp */
    createdDate datetime default current_timestamp,
    updatedDate datetime default current_timestamp on update current_timestamp
);

drop table if exists orders;
create table orders(
    orderID int primary key auto_increment,
    CustomerID int,
    dateOrdered date,
    dateRequired date,
    status tinyint,
    total float,
    discount tinyint,
    constraint fk2 foreign key (CustomerID) references customers(CustomerID)
);

drop table if exists ordersDetails;
create table ordersDetails(
    orderID int,
    bookID int,
    price float, /* gia tai thoi diem ban*/
    qty int,
    createdDate datetime default current_timestamp,
    updatedDate datetime default current_timestamp on update current_timestamp,
    constraint fk3 foreign key (orderID) references orders(orderID),
    constraint fk4 foreign key (bookID) references books(BookId)
);

drop table if exists users;
create table users(
    id int auto_increment,
    username nvarchar(50) not null,
    password nvarchar(50) not null,
    role int check ( role regexp '^[0-3]$'),
    constraint chk_password check (password regexp '^(?=.*[!@#$%^&*-])(?=.*[0-9])(?=.*[A-Z]).{8,20}'),
    constraint fk_id foreign key (id) references customers(CustomerID)
);

drop trigger if exists books_update_qty1;
drop trigger if exists books_update_qty2;
create trigger books_update_qty1 before update on books
    for each row
    begin
        if (NEW.status = 3 OR NEW.status = 2) then
            set new.qty = 0;
        end if;
    end;

create trigger books_update_qty2 before insert on books
    for each row
    begin
        if (NEW.status = 3 OR NEW.status = 2) then
            set NEW.qty = 0;
        end if;
    end;

insert into category values (1, 'Au My'),
                            (2, 'Code Developer'),
                            (3, 'Light Novel');
select * from category;

insert into books values (null, 1, 'The Humming Bird', 150000, 1, 2018, default, default, 20),
                         (null, 1, 'The Hunger Game', 100000, 1, 2008, default, default, 30),
                         (null, 1, 'The Great Gatsby', 100000, 1, 1925, default, default, 40),
                         (null, 1, 'The Tarzan', 70000, 1, 2016, default, default, 10),
                         (null, 2, 'BNWG for dummies', 80000, 3, 2014, default, default, 20),
                         (null, 2, 'Java for dummies', 110000, 1, 2019, default, default, 20),
                         (null, 2, 'Love or Hate JAVA??', 150000, 2, 2020, default, default, 20),
                         (null, 3, 'Kimi no na wa', 100000, 1, 2000, default, default, 20),
                         (null, 3, '5cm/s', 100000, 1, 2000, default, default, 20),
                         (null, 3, 'Arafoo Kenja No Isekai Seikatsu Nikki', 80000, 2, 2000, default, default, 20);
update books
    set Price = 100000
    where Name = 'The Humming Bird';
select * from books;

insert into customers values (null, 'Dinh Tien An', '100 Hang Dau', 1, '1999-11-7', 1, default, default),
                             (null, 'Dang Kim Thi', '100 Hang Muoi', 0, '2000-11-30', 3, default, default),
                             (null, 'Doan Van Toan', '100 Hang Tre', 1, '1999-12-1', 2, default, default),
                             (null, 'Nguyen Thi Thu Thao', '100 Hang Bac', 0, '2001-10-10', 1, default, default),
                             (null, 'Nguyen Giang Son', '100 Hang Be', 1, '1998-10-20', 1, default, default),
                             (null, 'Tran Duc Anh', '100 Hang Ma', 0, '1991-10-15', 2, default, default);
select * from customers;

insert into orders values (null, 1, '2020-10-5', '2020-10-10', 2, 500000, 10),
                          (null, 1, '2020-10-12', '2020-10-14', 1, 300000, 10),
                          (null, 2, '2020-10-6', '2020-10-10', 1, 350000, 10),
                          (null, 3, '2020-10-7', '2020-10-10', 3, 200000, 10),
                          (null, 5, '2020-10-3', '2020-10-10', 4, 900000, 10),
                          (null, 5, '2020-10-4', '2020-10-10', 1, 800000, 10),
                          (null, 5, '2020-9-1', '2020-9-5', 5, 150000, 8),
                          (null, 6, '2020-9-12', '2020-9-13', 5, 600000, 8),
                          (null, 6, '2020-9-14', '2020-9-15', 0, 700000, 8),
                          (null, 6, '2020-9-12', '2020-9-15', 0, 800000, 8);
select * from orders;

insert into ordersDetails values (1, 1, 100000, 2, default, default),
                                 (1, 2, 100000, 1, default, default),
                                 (1, 3, 100000, 2, default, default),
                                 (2, 4, 70000, 1, default, default),
                                 (2, 5, 80000, 1, default, default),
                                 (2, 7, 150000, 1, default, default),
                                 (3, 9, 100000, 1, default, default),
                                 (3, 8, 100000, 1, default, default),
                                 (3, 4, 70000, 1, default, default),
                                 (3, 5, 80000, 1, default, default),
                                 (4, 1, 100000, 1, default, default),
                                 (4, 2, 100000, 1, default, default),
                                 (5, 1, 100000, 5, default, default),
                                 (5, 2, 100000, 4, default, default),
                                 (6, 1, 100000, 2, default, default),
                                 (6, 2, 100000, 2, default, default),
                                 (6, 3, 100000, 2, default, default),
                                 (6, 4, 70000, 2, default, default),
                                 (6, 5, 80000, 2, default, default),
                                 (7, 4, 80000, 1, default, default),
                                 (7, 5, 70000, 1, default, default),
                                 (8, 1, 60000, 10, default, default),
                                 (8, 1, 60000, 10, default, default),
                                 (10, 3, 80000, 10, default, default);
select * from ordersDetails;

insert into users values (1, 'ilovekimchi', '123123Aa*', 1),
                         (2, 'ilovemumu' , '478123A8*a', 1),
                         (3, 'ilovekito', '123AaA3#$', 2);
select * from users;

# Test sql de select top 10 quyen sach moi nhat
    /*select * from books
        order by year desc limit 10;*/

# Test sql de select top 10 quyen sach ban chay nhat
    /*select oD.bookID, sum(oD.price * oD.qty) AS "SoTienBanDuoc" from books
        inner join ordersDetails oD on books.BookId = oD.bookID
        group by BookId
        order by sum(oD.price * oD.qty) desc limit 10;*/

# Tim sach theo the loai
     /*select * from books
        inner join category c on books.CatID = c.CatID
        where c.Name = ... (Ten loai sach nguoi dung nhap vao) */

# Tim sach theo ten tac gia
    /*select * from books
        where Name = ... (Ten tac gia nguoi dung nhap vao)*/

# Tim sach theo id
    /*select * from books
        where BookId = ... (ID cua cuon sach nguoi dung nhap vao)*/

# Tim 10 orders moi tiep nhan ( status 1)
    /*select * from orders
        where status = 1
        order by dateOrdered desc limit 10;*/

# Hien thi don hang theo ma KH
    /*select * from orders
        where CustomerID = ... (ID khach hang nguoi dung nhap vao)*/

# Hien thi trang thai don hang theo ma don hang
    /*select orderID, status from orders
        where orderID = ... (ID don hang nguoi dung nhap vao)*/

# Hien thi thong tin chi tiet don hang theo ma don duoc nhap vao
    /*select * from orders
        where orderID = ... (ID don hang nguoi dung nhap vao)*/