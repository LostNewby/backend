create table if not exists records
(
    id                      serial not null,
    user_id                 BIGINT not null,
    product_type_id         BIGINT not null,
    rating                  NUMERIC(10,2),
    price                   BIGINT default 0,
    quantity                BIGINT not null,
    limit_to_buy            BIGINT default 10,
    region                  varchar(225) not null,
    description             varchar(2000),
    unit                    varchar(30) not null,
    primary key (id)
    );