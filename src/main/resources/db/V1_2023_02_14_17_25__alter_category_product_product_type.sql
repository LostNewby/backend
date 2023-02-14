drop table if exists category;
drop table if exists product;
drop table if exists product_type;

create table if not exists category
(
    id                      serial not null,
    name_kz                 varchar(220) not null,
    name_ru                 varchar(220) not null,
    name_en                 varchar(220) not null,
    primary key (id)
    );

create table if not exists product
(
    id                      serial not null,
    name_kz                 varchar(220) not null,
    name_ru                 varchar(220) not null,
    name_en                 varchar(220) not null,
    category_id             BIGINT not null,
    primary key (id)
    );

create table if not exists product_type
(
    id                      serial not null,
    name_kz                 varchar(220) not null,
    name_ru                 varchar(220) not null,
    name_en                 varchar(220) not null,
    product_id              BIGINT not null,
    primary key (id)
    );