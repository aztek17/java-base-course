-- базовые запросы
select * from shop.products;

select * from shop.customers;

select * from shop.orders;

-- запросы с условием
select description, count
from shop.products
where count > 30
order by count asc;

select
    o.id as order_id,
    c.names as customer_name,
    o.count as ordered_count,
    p.description as product_name,
    p.price as unit_price,
    (o.count * p.price) as total
from shop.orders o
join shop.customers c on o.customer_id = c.id
join shop.products p on o.product_id = p.id
order by o.count asc;

-- обновление данных таблицы
update shop.products
set price = price * 0.75
where count > 30;

update shop.products
set price = price * 2, count = 0
where description like '%часы%';

update shop.orders as o
set count = 7
from shop.customers as c
where o.customer_id = c.id
and c.names like 'гусев%';

-- удаление данных таблицы
delete
from shop.orders
where order_date < '2024-01-01';

delete
from shop.products
where description like '%колонка%';

delete
from shop.customers
where id in(8,10);

