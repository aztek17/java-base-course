-- клиенты
create table public.users (
	age int4 null,
	id bigserial not null,
	email varchar(255) null,
	gender varchar(255) null,
	"name" varchar(255) null,
	phone varchar(255) null,
	constraint users_pkey primary key (id)
);

-- автомобили клиентов
create table public.cars (
	tire_size int4 null,
	id bigserial not null,
	user_id int8 null,
	brand varchar(255) null,
	model varchar(255) null,
	"type" varchar(255) null,
	constraint cars_pkey primary key (id),
    constraint cars_type_check check (
        "type" in ('PASSENGER', 'SUV', 'TRUCK', 'BUS', 'COMMERCIAL')
    ),
	constraint fkqw4c8e6nqrvy3ti1xj8w8wyc9 foreign key (user_id) references public.users(id)
);

-- услуги
create table public.services (
	duration int4 null,
	price numeric(38, 2) null,
	id bigserial not null,
	description varchar(255) null,
	service_name varchar(255) null,
	constraint services_pkey primary key (id)
);

-- заказы
create table public.orders (
	total_amount numeric(38, 2) null,
	car_id int8 null,
	completed_at timestamp(6) null,
	created_at timestamp(6) null,
	id bigserial not null,
	user_id int8 null,
	status varchar(255) null,
	constraint orders_pkey primary key (id),
	    constraint status_check check (
        status in ('NEW', 'IN_PROGRESS', 'COMPLETED', 'CANCELED')
    ),
	constraint fk32ql8ubntj5uh44ph9659tiih foreign key (user_id) references public.users(id),
	constraint fkd2p23ixwrrt395glgi9nnbj23 foreign key (car_id) references public.cars(id)
);

-- позиции заказа
create table public.order_items (
	price numeric(38, 2) null,
	quantity int4 null,
	id bigserial not null,
	order_id int8 null,
	service_id int8 null,
	constraint order_items_pkey primary key (id),
	constraint fkbioxgbv59vetrxe0ejfubep1w foreign key (order_id) references public.orders(id),
	constraint fkmm8mpcqgnkrs1ytqbv66bdxso foreign key (service_id) references public.services(id)
);

-- Вставка клиентов
insert into public.users (name, phone, email, age, gender) values
('Иванов Иван', '+79161234567', 'ivanov@mail.ru', 35, 'MALE'),
('Петрова Анна', '+79167654321', 'petrova@yandex.ru', 28, 'FEMALE'),
('Сидоров Алексей', '+79031234567', 'sidorov@gmail.com', 42, 'MALE'),
('Козлова Мария', '+79098765432', 'kozlova@mail.ru', 31, 'FEMALE'),
('Васильев Дмитрий', '+79115556677', 'vasilev@yandex.ru', 45, 'MALE'),
('Николаева Ольга', '+79119998877', 'nikolaeva@gmail.com', 29, 'FEMALE'),
('Федоров Сергей', '+79053332211', 'fedorov@mail.ru', 38, 'MALE');

-- Вставка автомобилей
insert into public.cars (user_id, brand, model, type, tire_size) values
(1, 'Toyota', 'Camry', 'PASSENGER', 17),
(1, 'Volkswagen', 'Tiguan', 'SUV', 19),
(2, 'Hyundai', 'Solaris', 'PASSENGER', 16),
(3, 'Ford', 'Focus', 'PASSENGER', 16),
(4, 'KIA', 'Sorento', 'SUV', 18),
(4, 'Renault', 'Logan', 'PASSENGER', 15),
(5, 'LADA', 'Vesta', 'PASSENGER', 16),
(6, 'BMW', 'X5', 'SUV', 20),
(7, 'Mercedes-Benz', 'Sprinter', 'COMMERCIAL', 17);

-- Вставка услуг
insert into public.services (service_name, description, price, duration) values
('Замена покрышки', 'Демонтаж/монтаж одной покрышки на диск', 500.00, 15),
('Балансировка колеса', 'Балансировка одного колеса на станке', 300.00, 20),
('Ремонт прокола', 'Ремонт прокола методом холодной вулканизации', 400.00, 30),
('Замена вентиля', 'Замена ниппеля (вентиля) колеса', 100.00, 10),
('Перебортировка', 'Перебортировка колеса (снятие/установка покрышки)', 600.00, 25),
('Хранение шин', 'Сезонное хранение комплекта шин (4 шт.)', 2000.00, 5),
('Развал-схождение', 'Проверка и регулировка развала-схождения', 1500.00, 60),
('Диагностика подвески', 'Комплексная диагностика ходовой части', 800.00, 40);