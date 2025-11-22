create schema if not exists shop;

-- создание таблицы products в схеме shop
create table if not exists shop.products (
    id serial primary key,
    description varchar(750),
    price int not null check (price >= 0),
    count int not null check (count >= 0)
);
-- Описание таблицы products
comment on table shop.products is 'Информация о товарах: описание товара, цена, доступное количество';
comment on column shop.products.id is 'Идентификатор товара';
comment on column shop.products.description is 'Описание товара';
comment on column shop.products.price is 'Цена товара';
comment on column shop.products.count is 'Доступное количество товара';

-- создание таблицы customers в схеме shop
create table if not exists shop.customers (
    id serial primary key,
    names varchar(80) not null
);
-- Описание таблицы customers
comment on table shop.customers is 'Информация о покупателях: фамилия/имя покупателя';
comment on column shop.customers.id is 'Идентификатор покупателя';
comment on column shop.customers.names is 'Фамилия/Имя покупателя';

-- создание таблицы orders в схеме shop
create table if not exists shop.orders (
    id serial primary key,
    product_id int not null,
    customer_id int not null,
    order_date date not null default current_date,
    count int not null check (count > 0),
    
    foreign key (product_id) references shop.products(id) on delete cascade,
    foreign key (customer_id) references shop.customers(id) on delete cascade
);
-- Описание таблицы orders
comment on table shop.orders is 'Информация о заказах';
comment on column shop.orders.id is 'Идентификатор заказа';
comment on column shop.orders.product_id is 'ID товара из таблицы products';
comment on column shop.orders.customer_id is 'ID покупателя из таблицы customers';
comment on column shop.orders.order_date is 'Дата заказа';
comment on column shop.orders.count is 'Количество товаров в заказе';

-- Заполнение таблицы products
insert into shop.products (description, price, count) values
('Смартфон Samsung Galaxy S25 Ultra', 102000, 31),
('Смартфон Apple iPhone 16 Pro Max 256 ГБ черный', 125000, 8),
('6.67" Смартфон Xiaomi Redmi Note 14 128 ГБ серый', 17000, 44),
('Смарт-часы HUAWEI WATCH GT 6 Pro 46 mm', 12000, 77),
('Смарт-часы Apple Watch Series 10 42 mm', 39000, 21),
('Смарт-часы Samsung Galaxy Watch Ultra 2025 47 mm', 40000, 10),
('Портативная колонка JBL Charge 6', 14000, 79),
('Портативная колонка Marshall Emberton 2', 15000, 33),
('Портативная колонка Philips TAS7807B', 4000, 32),
('Кабель оптический Philips ODT Toslink', 3000, 5);

-- Заполнение таблицы customers
insert into shop.customers (names) values
('Иванов Сергей'),
('Ромашкина Мария'),
('Гусев Максим'),
('Уткин Егор'),
('Загадкина Татьяна'),
('Смотрелов Денис'),
('Летный Евгений'),
('Далеков Игорь'),
('Светлова Инна'),
('Маркова Ангелина');

-- Заполнение таблицы orders
insert into shop.orders (product_id, customer_id, order_date, count) values
(1, 5, '2023-05-17', 1),
(5, 1, '2023-07-20', 2),
(2, 4, '2023-07-28', 1),
(4, 2, '2023-08-10', 3),
(3, 3, '2023-12-11', 1),
(7, 1, '2024-04-12', 2),
(1, 2, '2024-06-06', 3),
(6, 7, '2025-01-13', 1),
(7, 6, '2025-01-13', 3),
(10, 9, '2025-01-24', 5);