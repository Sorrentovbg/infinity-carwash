-- drop schema if exists product_sh cascade;

create schema if not exists product_sh ;

create table product_tb
(
    id                        bigserial primary key,
    product_name              varchar(250) not null,
    product_short_description varchar(300) not null,
    product_full_description  varchar(800) not null,
    product_price             integer      not null,
    job_duration              integer      not null
);

insert into product_tb( product_name,
                        product_short_description,
                        product_full_description,
                        product_price,
                        job_duration) values
    ('Мойка','Это очень качественная мойка','очень длинное описание',350, 30),
    ('Пылеровка','Бричка будет блестеть!','очень длинное описание' , 1000, 60),
    ('Уборка салона','Чтоб приятно было в машину сесть','очень длинное описание', 1500, 90);
