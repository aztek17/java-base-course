create schema if not exists televisions;

-- создание таблицы tv
create table if not exists televisions.tv
-- определяем столбцы
(
    id serial primary key,          -- идентификатор строки, всегда уникальный, генерируется базой данных
    brand varchar(80),
    model varchar(80),
    price int,
    voice_assistant boolean,
    screen_size int
);

insert into televisions.tv (brand, model, price, voice_assistant, screen_size)
values ('Яндекс ТВ', 'YNDX-00072', 30000, true, 55),
('Xiaomi', 'TV A PRO 60', 55000, true, 60),
('Samsung', 'UE43DU7100UXRU', 27000, false, 43),
('LG', 'OLED65C5RLA', 174500, true, 65),
('Hisense', '55U7Q', 58300, false, 55);