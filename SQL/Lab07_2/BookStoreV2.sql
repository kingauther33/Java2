Use ebookstore;

insert into books(BookId, Name, Price, qty, status) values ()

update books
    set qty = ..;

select * from ordersdetails;

delete from ordersdetails
    where bookID = ...;

insert into customers(CustomerID, Name, Gender, level) values ();

select * from customers;

update customers
    set Address = '...',
        Gender = 0,
        level = 0;

select * from customers where CustomerID = 1;

select * from books
    order by year desc
    limit 10;
