-- 书表
create table book(
    isbn varchar(20) primary key comment '唯一标准码',
    price double comment '价格'
);

-- 库存表
create table book_stock(
    isbn varchar(20) primary key comment '唯一标准码',
    stock int comment '库存数'
);

-- 账户表
create table account(
    id int auto_increment primary key comment '唯一id',
    balance double unsigned comment '余额'
);

