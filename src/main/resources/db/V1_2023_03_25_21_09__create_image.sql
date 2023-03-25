create table if not exists images
(
    record_id               BIGINT not null,
    link                    varchar(225),
    primary key (record_id)
    );