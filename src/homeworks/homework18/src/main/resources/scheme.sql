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

-- добавление информации
insert into shop.customer(customer_name)
values ('Петров Николай'),
('Спиридонов Дмитрий'),
('Марьина Дарья');

insert into shop.orders(customer_id, order_date, count_order, discount)
values ('1', '2025-06-15', 1, 0),
('2', '2025-12-07', 1, 10),
('3', '2024-03-18', 1, 5),
('1', '2025-12-20', 2, 15),
('1', '2025-12-21', 3, 7);

commit;