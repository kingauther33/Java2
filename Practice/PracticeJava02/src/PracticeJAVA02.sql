drop database if exists practiceJAVA02;

create database if not exists practiceJAVA02;

use practiceJAVA02;

create table Student (
    StudentID varchar(50) primary key,
    Name nvarchar(100),
    Address nvarchar(100),
    Phone varchar(50) check ( Phone REGEXP '[0-9]{8,15}')
);

insert into Student values ('B010110', 'Nguyen Tuan Anh', 'Ha Noi', '0904818238'),
                           ('B10394', 'Nguyen Hoang Hai', 'Hai Duong', '0494949494');

select * from Student;

insert into Student values ()


