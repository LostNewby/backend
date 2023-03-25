create table if not exists purchase_info
(
    id                      serial not null,
    costumer_id             BIGINT not null,
    record_id               BIGINT not null,
    quantity                BIGINT not null,
    costumer_phone          varchar(50) not null,
    status                  varchar(50) not null,
    creation_date           timestamp not null,
    completion_date         timestamp,
    primary key (id)
    );
