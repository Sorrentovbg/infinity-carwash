create schema if not exists order_sh;

create table order_tb(
     id                  bigserial
                         primary key,
     customer_name       varchar(250) not null,
     car_number          varchar(50) not null,
     product_name        varchar(15) not null,
     data                date not null,
     time                time not null
);

insert into order_tb(customer_name, car_number, product_name, data, time) values
    ('Михаил', 'х952рл47', 'мойка', '2021-07-18', '09:30:00'),
    ('Кирилл', 'х852рл78', 'мойка', '2021-07-15', '12:30:00'),
    ('Кирилл', 'х855рл78', 'мойка', '2021-07-15', '15:00:00'),
    ('Александр', 'з875ке147', 'мойка', '2021-07-18', '10:00:00');

