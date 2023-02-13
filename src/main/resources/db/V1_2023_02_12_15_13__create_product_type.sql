create table if not exists product_type
(
    id                      serial not null,
    name                    varchar(220) not null,
    product_id              BIGINT not null,
    primary key (id)
    );