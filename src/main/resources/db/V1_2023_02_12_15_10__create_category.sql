create table if not exists category
(
    id                      serial not null,
    name                    varchar(220) not null,
    primary key (id)
    );