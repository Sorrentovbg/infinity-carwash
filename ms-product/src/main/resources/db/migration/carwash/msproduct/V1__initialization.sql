-- drop schema if exists product_sh cascade;

create schema if not exists product_sh ;

create table product_tb (
    id          bigserial primary key,
    product_name        varchar(250) not null,
    product_description varchar(500) not null,
    product_price       integer not null,
    job_duration        integer not null
);

insert into product_tb(product_name,product_description, product_price, job_duration) values
    ('Мойка','Это оченб качественная мойка' ,350, 30),
    ('Пылеровка','Бричка будет блестеть!' , 1000, 60),
    ('Уборка салона','Чтоб приятно было в машину сесть', 15000, 90);