create schema if not exists shop;

-- создание таблицы customer
create table if not exists shop.customer
-- определяем столбцы
(
    id serial primary key,          -- идентификатор строки, всегда уникальный, генерируется базой данных
    customer_name varchar(30)       -- строка максимальной длины 30
);

-- создание таблицы orders
create table if not exists shop.orders
-- определяем столбцы
(
    id serial primary key,          -- идентификатор строки, всегда уникальный, генерируется базой данных
    customer_id int,
    order_date date,
    count_order int,
    discount int,

    foreign key (customer_id) references shop.customer (id)
);