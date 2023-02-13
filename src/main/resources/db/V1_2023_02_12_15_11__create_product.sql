create table if not exists product
(
    id                      serial not null,
    name                    varchar(220) not null,
    category_id             BIGINT not null,
    primary key (id)
    );