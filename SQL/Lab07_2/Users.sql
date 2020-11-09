Use ebookstore;

drop table if exists users;

create table users(
    id int auto_increment,
    username nvarchar(50) not null,
    password nvarchar(50) not null,
    role int check ( role regexp '^[0-3]$'),
    constraint chk_password check (password regexp '^(?=.*[!@#$%^&*-])(?=.*[0-9])(?=.*[A-Z]).{8,20}'),
    constraint fk_id foreign key (id) references customers(CustomerID)
);

insert into users values (1, 'ilovekimchi', '123123Aa*', 1),
                         (2, 'ilovemumu' , '478123A8*a', 1),
                         (3, 'ilovekito', '123AaA3#$', 2);

select * from users limit 1;

select count(1) from users
    where username = 'abc' and password = 'awef';

update users
    set username = 'a',
        password = 'awe',
        role = 0
    where username = 'ilovekimchi'


