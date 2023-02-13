create table if not exists ratings
(
    id                      serial not null,
    user_id                 BIGINT not null,
    record_id               BIGINT not null,
    rating_stars            NUMERIC(10,2) not null,
    description             varchar(2000),
    primary key (id)
    );