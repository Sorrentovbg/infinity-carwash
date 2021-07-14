CREATE SCHEMA if not exists user_sh;

create table role_tb(
    id   bigserial      not null
        constraint role_tb_pk
            primary key,
    name varchar(20) not null
);

create table user_tb(
    id       bigserial not null
        constraint user_tb_pk
            primary key,
    login    varchar(50),
    password varchar(500),
    role_id  bigint
        constraint user_tb_role_tb_id_fk
            references role_tb
);

insert into role_tb(name) values
    ('ROLE_ADMIN'),
    ('ROLE_USER');

insert into user_tb (login, password,role_id) values
('admin', '1',1),
('user', '1',1);
