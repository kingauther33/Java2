use northwind;

update categories
    set CategoryName = 'SeaFoodVN'
    where CategoryName = 'Seafood';

select * from categories;

update customers
    set Address = '1A Yet Kieu - Ha Noi'
    where CustomerID = 'FRANK';

update products
    set UnitPrice = UnitPrice * 1.1
    where CategoryID IN (5, 7, 8);

select * from products;

update orders
    set ShipVia = 3
    where ShipVia = 2 AND OrderID = 10313;
