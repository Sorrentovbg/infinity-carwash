create schema if not exists order_sh;

create table status (
    status_id       bigserial primary key,
    orders_status   varchar(35) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table order_tb(
    id                  bigserial
                        primary key,
    user_id             bigserial not null,
    product_id          bigserial not null,
    car_number          varchar(50) not null,
    data                date not null,
    time                time not null,
    created_at timestamp default current_timestamp
);
insert into status (orders_status) values
('Not paid'),
('Paid'),
('Completed');


insert into order_tb(user_id, product_id, car_number, data, time) values
    (1, 1,'х952рл47', '2021-07-18', '09:30:00'),
    (2, 1 ,'х852рл78', '2021-07-16', '12:30:00'),
    (3, 1, 'х855рл78', '2021-07-16', '15:00:00'),
    (1, 1, 'з875ке147', '2021-07-18', '10:00:00');



